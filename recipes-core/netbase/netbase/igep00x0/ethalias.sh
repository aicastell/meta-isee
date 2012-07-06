#! /bin/sh

# eth0:0 with dhcp address

if [ -x /sbin/udhcpc ]; then
	/sbin/udhcpc -n -i eth0:0
fi

exit 0
