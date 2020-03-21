# crashdemos

03-15 08:42:01.271: I/com.demo.crash(5596): Explicit concurrent copying GC freed 6(32KB) AllocSpace objects, 0(0B) LOS objects, 24% free, 2138KB/2851KB, paused 79us total 30.808ms


正常调试时
sirius:/ $ ps -e | grep demo
u0_a295       6822   647 5212492 114036 0                   0 S com.demo.jnicrash


sirius:/ $ ps -e | grep 647
root           390     2       0      0 0                   0 S [irq/647-tempera]
system         507   647 7237920 186876 0                   0 S com.android.systemui
root           647     1 5478268  45740 0                   0 S zygote64
