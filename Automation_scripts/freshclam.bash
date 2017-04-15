#! /bin/bash

. /etc/rc.common

LOGGER=/var/log/self-logs/freshclam/runs.log
LOGGERTMP=/var/log/self-logs/freshclam/tmp.log
NOTIFY_TITLE='Freshclam CRON'

function notify () {
	osascript -e "display notification \"$2\" with title \"$1\""
}


CheckForNetwork

echo "----------------------------------"
echo "freshclam run $(date)"
echo "----------------------------------"

if [[ "${NETWORKUP}" != '-YES-' ]]; then
	net_fail_msg="No network available, exiting\n"
	printf "$net_fail_msg" >&2
	echo -e "$net_fail_msg"
	echo; echo;

	notify "$NOTIFY_TITLE" "No network available, service exiting"
	exit 126
fi

/usr/local/bin/freshclam -v

if [[ $? == 0 ]]; then
	notify "$NOTIFY_TITLE" "Done updating Freshclam VirusDB"
	echo; echo;
	exit 0
else
	notify "$NOTIFY_TITLE" "Failed updating Freshclam VirusDB\nLog info in '/tmp/freshclam.job.err'"
	exit 1
fi
