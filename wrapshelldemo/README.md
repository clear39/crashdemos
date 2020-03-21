# crashdemos

03-15 08:42:01.271: I/com.demo.crash(5596): Explicit concurrent copying GC freed 6(32KB) AllocSpace objects, 0(0B) LOS objects, 24% free, 2138KB/2851KB, paused 79us total 30.808ms

第一种调试脚本：
LIBC_DEBUG_MALLOC_OPTIONS=backtrace logwrapper "$@"
sirius:/ $ ps -e |grep demo
u0_a291       3467  3465 5129012 204440 0                   0 S com.demo.wrapshell.debug
127|sirius:/ $ ps -e | grep 3465
u0_a291       3465  3462   28076   2888 0                   0 S logwrapper
u0_a291       3467  3465 5129012 203928 0                   0 S com.demo.wrapshell.debug
sirius:/ $ ps -e | grep 3462
u0_a291       3462  3457   27340   2168 0                   0 S sh
u0_a291       3465  3462   28076   2888 0                   0 S logwrapper
sirius:/ $ ps -e | grep 3457
u0_a291       3457  3429   27308   2212 0                   0 S logwrapper
u0_a291       3462  3457   27340   2168 0                   0 S sh
sirius:/ $ ps -e|grep 3429
u0_a291       3429   647   27340   2216 0                   0 S sh
u0_a291       3457  3429   27308   2212 0                   0 S logwrapper
1|sirius:/ $ ps -e | grep 647
root           647     1 5478268  45696 0                   0 S zygote64


第二种调试脚本：
LIBC_DEBUG_MALLOC_OPTIONS=backtrace "$@"

130|sirius:/ $ ps -e | grep demo
u0_a292       4322  4321 5150116 209004 0                   0 S com.demo.wrapshell.debug
sirius:/ $ ps -e | grep 4321
u0_a292       4321  4320   27340   2212 0                   0 S sh
u0_a292       4322  4321 5133288 210260 0                   0 S com.demo.wrapshell.debug
sirius:/ $ ps -e | grep 4320
u0_a292       4320  4275   27308   2328 0                   0 S logwrapper
u0_a292       4321  4320   27340   2212 0                   0 S sh
sirius:/ $ ps -e | grep 4275
u0_a292       4275   647   27340   2192 0                   0 S sh
u0_a292       4320  4275   27308   2328 0                   0 S logwrapper
1|sirius:/ $ ps -e | grep 647
root           647     1 5478268  45696 0                   0 S zygote64


xqli@linux:/work/workcodes/android-demos/CrashDemos/wrapshelldemo/build$ find -name libnative-lib.so
./intermediates/merged_native_libs/release/out/lib/arm64-v8a/libnative-lib.so
./intermediates/merged_native_libs/release/out/lib/x86_64/libnative-lib.so
./intermediates/merged_native_libs/release/out/lib/x86/libnative-lib.so
./intermediates/merged_native_libs/release/out/lib/armeabi-v7a/libnative-lib.so
./intermediates/merged_native_libs/debug/out/lib/arm64-v8a/libnative-lib.so
./intermediates/merged_native_libs/debug/out/lib/x86_64/libnative-lib.so
./intermediates/merged_native_libs/debug/out/lib/x86/libnative-lib.so
./intermediates/merged_native_libs/debug/out/lib/armeabi-v7a/libnative-lib.so
./intermediates/cmake/release/obj/arm64-v8a/libnative-lib.so
./intermediates/cmake/release/obj/x86_64/libnative-lib.so
./intermediates/cmake/release/obj/x86/libnative-lib.so
./intermediates/cmake/release/obj/armeabi-v7a/libnative-lib.so
./intermediates/cmake/debug/obj/arm64-v8a/libnative-lib.so
./intermediates/cmake/debug/obj/x86_64/libnative-lib.so
./intermediates/cmake/debug/obj/x86/libnative-lib.so
./intermediates/cmake/debug/obj/armeabi-v7a/libnative-lib.so
./intermediates/stripped_native_libs/release/out/lib/arm64-v8a/libnative-lib.so
./intermediates/stripped_native_libs/release/out/lib/x86_64/libnative-lib.so
./intermediates/stripped_native_libs/release/out/lib/x86/libnative-lib.so
./intermediates/stripped_native_libs/release/out/lib/armeabi-v7a/libnative-lib.so
./intermediates/stripped_native_libs/debug/out/lib/arm64-v8a/libnative-lib.so
./intermediates/stripped_native_libs/debug/out/lib/x86_64/libnative-lib.so
./intermediates/stripped_native_libs/debug/out/lib/x86/libnative-lib.so
./intermediates/stripped_native_libs/debug/out/lib/armeabi-v7a/libnative-lib.so


xqli@linux:/work/workcodes/android-demos/CrashDemos/wrapshelldemo/build$ file ./intermediates/cmake/debug/obj/arm64-v8a/libnative-lib.so
./intermediates/cmake/debug/obj/arm64-v8a/libnative-lib.so: ELF 64-bit LSB shared object, ARM aarch64, version 1 (SYSV), dynamically linked, BuildID[sha1]=50afbf6fa9c3519efdae5c5ee3640fe213a7eaae, with debug_info, not stripped
xqli@linux:/work/workcodes/android-demos/CrashDemos/wrapshelldemo/build$ file ./intermediates/merged_native_libs/debug/out/lib/arm64-v8a/libnative-lib.so
./intermediates/merged_native_libs/debug/out/lib/arm64-v8a/libnative-lib.so: ELF 64-bit LSB shared object, ARM aarch64, version 1 (SYSV), dynamically linked, BuildID[sha1]=50afbf6fa9c3519efdae5c5ee3640fe213a7eaae, with debug_info, not stripped

