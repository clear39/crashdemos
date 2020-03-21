#!/system/bin/sh
# LIBC_DEBUG_MALLOC_OPTIONS=backtrace logwrapper "$@"
LIBC_DEBUG_MALLOC_OPTIONS=backtrace "$@"