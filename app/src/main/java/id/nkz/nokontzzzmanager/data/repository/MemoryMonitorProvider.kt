package id.nkz.nokontzzzmanager.data.repository

import android.content.Context
import id.nkz.nokontzzzmanager.data.model.MemoryInfo
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemoryMonitorProvider @Inject constructor(
    private val context: Context,
    private val tuningRepository: TuningRepository,
    private val nativeTelemetryReader: NativeTelemetryReader,
) {
    private suspend fun getMemoryInfoInternal(): MemoryInfo {
        return try {
            val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as android.app.ActivityManager
            val memoryInfo = android.app.ActivityManager.MemoryInfo()
            activityManager.getMemoryInfo(memoryInfo)

            val nativeZram = nativeTelemetryReader.readSnapshot()?.zram

            val zramTotal = nativeZram?.disksizeBytes?.takeIf { it > 0L }
                ?: tuningRepository.getZramDisksize().firstOrNull() ?: 0L
            val zramUsed = nativeZram?.usedBytes?.takeIf { it > 0L }
                ?: tuningRepository.getZramUsed().firstOrNull() ?: 0L
            val swapTotal = nativeZram?.swapTotalBytes?.takeIf { it > 0L }
                ?: tuningRepository.getSwapTotal().firstOrNull() ?: 0L
            val swapUsed = nativeZram?.swapUsedBytes?.takeIf { it > 0L }
                ?: tuningRepository.getSwapUsed().firstOrNull() ?: 0L

            MemoryInfo(
                used = memoryInfo.totalMem - memoryInfo.availMem,
                total = memoryInfo.totalMem,
                free = memoryInfo.availMem,
                zramTotal = zramTotal,
                zramUsed = zramUsed,
                swapTotal = swapTotal,
                swapUsed = swapUsed
            )
        } catch (e: Exception) {
            MemoryInfo(0, 0, 0)
        }
    }

    fun getMemoryInfo(): MemoryInfo {
        return runBlocking { getMemoryInfoInternal() }
    }

    suspend fun getMemoryInfoSuspend(): MemoryInfo = getMemoryInfoInternal()
}
