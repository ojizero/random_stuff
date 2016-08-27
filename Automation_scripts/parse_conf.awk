#! /bin/awk -f

######################################################
##	An AWK script to parse through config files		##
##	Used together with declare $() it creates		##
##	Shell variables with each option and it's value	##
##	from the config file given as input				##
######################################################


# NOTEtoSELF: using [...] around # isn't necessary, but it makes synatx highlight usable
!/^[#][^!].*$/ && !/^\s*$/ {
	# split line by '='
	split ($0, l, "=");
	# trim whitespaces from the line parts and remove inline comments
	gsub (/(^\s+|\s+$|[#].*[^'"]+.*$)/, "", l[1]);
	gsub (/(^\s+|\s+$|[#].*[^'"]+.*$)/, "", l[2]);
	# print the first part (option) with equals sign next to the second part (value)
	# outputs Option=Value to shell
	print l[1]"="l[2];
}
