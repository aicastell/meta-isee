#! /bin/sh
### BEGIN INIT INFO
# Provides:          auto mount /boot
# Required-Start:
# Required-Stop:
# Default-Start:
# Default-Stop:      0
# Description:       Automatically MOUNT the partition where
#                    Kernel, u-boot MLO, uEnv and dtb resides
#                    into /boot folder
#
### END INIT INFO

evaluate=$(cat /proc/cmdline | cut -d'/' -f3 | cut -d' ' -f1)
part='p'
case $evaluate in
        nfs) echo "igep: nfs boot -_- /boot not populated" ;;
        *)
        base=$(echo $evaluate | cut -d 'p' -f1)
        num=$(echo $evaluate | cut -d 'p' -f2)
        end=$(expr $num - 1)
        final=$base$part$end
        mount -t vfat /dev/$final /boot -o rw,sync,noauto
        ;;
esac

