#! /bin/bash


LOGGER=/var/log/self-logs/freshclam/runs.log
LOGGERTMP=/var/log/self-logs/freshclam/tmp.log
NOTIFY_TITLE='Freshclam CRON'

function notify () {
	osascript -e "display notification \"$2\" with title \"$1\""
}


echo "----------------------------------"
echo "freshclam run $(date)"
echo "----------------------------------"

/usr/local/bin/freshclam -v

if [[ $? == 0 ]]; then
	notify "$NOTIFY_TITLE" "Done updating Freshclam VirusDB"
else
	notify "$NOTIFY_TITLE" "Failed updating Freshclam VirusDB\nLog info in '/tmp/freshclam.job.err'"
fi
