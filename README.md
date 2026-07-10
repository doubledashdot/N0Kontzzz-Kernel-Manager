<p align="center">
  <img src="nkm-logo.png" alt="NKM Logo" width="120">
</p>

<h1 align="center">N0Kontzzz Kernel Manager</h1>

<p align="center">
  <img src="https://img.shields.io/badge/platform-Android-green?style=for-the-badge&logo=android" alt="Platform">
  <img src="https://img.shields.io/badge/language-Kotlin-purple?style=for-the-badge&logo=kotlin" alt="Kotlin">
  <img src="https://img.shields.io/badge/native-Rust-orange?style=for-the-badge&logo=rust" alt="Rust">
  <img src="https://img.shields.io/badge/Jetpack-Compose-blue?style=for-the-badge&logo=jetpackcompose" alt="Compose">
  <img src="https://img.shields.io/badge/Root-Required-critical?style=for-the-badge&logo=android" alt="Root Required">
</p>

<p align="center">
  <a href="https://github.com/bimoalfarrabi/N0Kontzzz-Kernel-Manager/blob/main/LICENSE"><img src="https://img.shields.io/github/license/bimoalfarrabi/N0Kontzzz-Kernel-Manager?style=for-the-badge" alt="License"></a>
  <a href="https://github.com/bimoalfarrabi/N0Kontzzz-Kernel-Manager"><img src="https://img.shields.io/github/repo-size/bimoalfarrabi/N0Kontzzz-Kernel-Manager?style=for-the-badge&logo=github" alt="Repo Size"></a>
  <a href="https://github.com/bimoalfarrabi/N0Kontzzz-Kernel-Manager/releases"><img src="https://img.shields.io/github/downloads/bimoalfarrabi/N0Kontzzz-Kernel-Manager/total?color=%233DDC84&logo=android&logoColor=%23fff&style=for-the-badge" alt="Downloads"></a>
  <a href="https://t.me/n0kontzzz"><img src="https://img.shields.io/badge/Telegram-Join-2CA5E0?style=for-the-badge&logo=telegram&logoColor=white" alt="Telegram"></a>
</p>

<p align="center">
  Real-time hardware telemetry and deep kernel tuning for the Poco F4 (munch).<br>
  Built with Kotlin + Jetpack Compose, with a Rust native telemetry layer for low-overhead sysfs reads.
</p>

> [!CAUTION]
> **Use this application at your own risk.**
> This utility performs advanced system-level operations and kernel tuning that may impact device stability, cause data loss, or potentially damage hardware if misconfigured. The developers assume no responsibility for any issues or damages resulting from the use of this software.

**N0Kontzzz Kernel Manager** is an Android root utility for the Poco F4 (munch). Real-time hardware telemetry, deep kernel tuning, and a per-app profile engine — all through a Material Design 3 interface. The telemetry layer is backed by a Rust native module for low-overhead sysfs reads with a Kotlin fallback for root-only paths.

---

## Features

### Monitoring

Live dashboard across all critical hardware components.

| Component | What you see |
|---|---|
| CPU | Per-core frequency, load %, temperature |
| GPU | Clock speed and utilization |
| Memory | RAM and ZRAM usage with compression stats |
| Storage | Internal filesystem breakdown |
| Battery | Voltage, current (mA), power (W), health |

### Battery Analytics

Tracks full charge/discharge sessions: average and peak currents, temperatures, screen-on/off drain rates, deep sleep ratio, and charging speed. Resets on reboot, charge event, or a configured battery level.

### FPS Meter & Benchmarking

- Floating overlay showing FPS, 1% Low, and Frame Time — draggable, auto-dims when idle
- Record sessions for detailed reports: Avg/Min/Max FPS, 0.1% Low, jank count, frame time jitter, CPU cluster frequencies, GPU usage, power draw, and thermal data
- Review past sessions with multi-line charts and duration summaries

### Per-App Profiles

Profiles activate automatically when an app enters the foreground. Each profile configures:

- CPU governor and min/max frequencies per cluster (Little / Big / Prime)
- GPU frequency and power level
- Thermal profile
- KGSL Skip Pool Zeroing, Avoid Dirty PTE, Bypass Charging

### Wakelock Monitor

Real-time tracking of system and kernel wakelocks — active count, wakeup frequency, total prevent-suspend duration. Heuristic labels flag high-impact wakelocks to simplify idle drain debugging.

### Performance Mode

One-tap governor presets across all clusters.

| Mode | Governor | Use case |
|---|---|---|
| Balanced | `schedutil` | Daily driver |
| Performance | `performance` | Gaming, benchmarks |
| Powersave | `powersave` | Extending battery life |

### Kernel Tuning

| Feature | What it does |
|---|---|
| CPU Tuning | Min/max frequency and governor per cluster (Little, Big, Prime) |
| GPU Control | Governor, min/max frequency, power throttle level |
| RAM & ZRAM | Compression algorithm switch; `swappiness`, dirty ratios, `min_free_kbytes` |
| Thermal Management | Throttle profiles from conservative to permissive |
| TCP Congestion | Switch algorithms (BBR, Cubic, Reno…); persist across reboots |
| I/O Scheduler | Scheduler selection; persist across reboots |
| Background App Blocker | Kernel `bg_blocklist` node to restrict background consumption |
| Bypass Charging | Pause battery charging; power direct from adapter |
| Charging Control | Automated ceiling/floor charge cycle |
| USB Fast Charge | Force higher charging current *(use with caution)* |
| KGSL Skip Pool Zeroing | Skip Adreno GPU page zero-init to reduce overhead |
| Avoid Dirty PTE | Reduce dirty-page clearing overhead |
| Custom Tunables | Read/write any sysfs/procfs node; optional Apply on Boot |

### Other

- **Kernel Log** — live `dmesg` viewer with keyword search and full export
- **Dexopt** — manual ART profile compilation (`speed-profile`, `layout`) and background dexopt
- **Intelligent Backup & Restore** — selective backup; restore validates every value against the current kernel before applying

### Modern UI

- Material Design 3 Expressive components
- Light, Dark, and System-adaptive themes with AMOLED pure-black mode
- Customizable notification icon (battery %, app logo, or transparent)
- Fully localized in English and Indonesian
- Permission Manager showing all required permissions and their status

---

## Requirements

- Kona (SM8250) device running a supported kernel:
  `N0Kontzzz` · `N0kernel` · `FusionX` · `Lunar` · `E404R` · `perf+` · `Oxygen+` · `dead-butterflies`
- Root access via **Magisk** or **KernelSU**

---

## Permissions

| Permission | Purpose |
|---|---|
| Root (`ACCESS_SUPERUSER`) | Kernel-level operations via `libsu` |
| Dump | Dexopt command execution and monitoring |
| Usage Access (`PACKAGE_USAGE_STATS`) | Per-App Profiles foreground detection |
| Storage (`MANAGE_EXTERNAL_STORAGE`) | Backup and restore via SAF |
| Query All Packages | Per-App Profile app list |
| Display Over Other Apps | FPS Meter overlay |
| Battery Optimization Ignore | Background monitoring stability |
| Vibrate | Haptic feedback on sliders |
| Post Notifications | Battery Monitor and Thermal Service notifications |
| Boot Completed | Re-apply profiles after reboot |
| Foreground Service | Keep monitoring services alive |

**No internet permission is requested. The app runs entirely offline.**

---

## Technology Stack

| Layer | Library |
|---|---|
| Language | [Kotlin](https://kotlinlang.org/) + Coroutines + Flow · [Rust](https://www.rust-lang.org/) (native telemetry) |
| UI | [Jetpack Compose](https://developer.android.com/jetpack/compose) + Material Design 3 |
| Architecture | MVVM |
| Root shell | [libsu](https://github.com/topjohnwu/libsu) |
| DI | [Dagger Hilt](https://dagger.dev/hilt/) |
| Database | [Room](https://developer.android.com/training/data-storage/room) |
| Background tasks | WorkManager + Foreground Services |
| Preferences | DataStore |
| Serialization | [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization) |
| Image loading | [Coil](https://coil-kt.github.io/coil/) |
| Navigation | Compose Navigation |

---

> [!TIP]
>
> - Start with **Balanced** (`schedutil`) for daily use — best battery-to-performance ratio.
> - Switch to **Performance** only for gaming or benchmarks; temperatures rise quickly.
> - Use **Per-App Profiles** to automate this: Performance for your game, Balanced everywhere else.
> - Enable **Bypass Charging** during long sessions plugged in to protect battery health.

---

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md) for setup instructions, code style, and PR guidelines.

Issues and feature requests are welcome at [github.com/bimoalfarrabi/N0Kontzzz-Kernel-Manager](https://github.com/bimoalfarrabi/N0Kontzzz-Kernel-Manager).

For community support, join the [Telegram group](https://t.me/n0kontzzz).

---

## Acknowledgments

- **[Xtra Kernel Manager](https://github.com/Gustyx-Power/Xtra-Kernel-Manager)** — The foundational project for this application.
- **[Danda](https://github.com/Danda420)** — Significant contributions to development and insights into Android system internals.
- **[RvKernel Manager](https://github.com/Rve27/RvKernel-Manager)** — Inspiration for specific features and implementation references.
- Poco F4 Community — For ongoing support and feedback.
