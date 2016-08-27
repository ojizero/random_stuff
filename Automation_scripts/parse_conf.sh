#! /bin/sh


##############################################################
##	Wrapper to invoke parse_conf.awk and pass it to declare	##
##	effectively creating shell variables for each option in	##
##	the config file and set it to it's value				##
##############################################################

# First argument is config file

# An optional second argument is the separator to use
# An optional third argument is the parser's path
if [[ $# -eq 2 ]]; then
	sep="$2"
elif [[ $# -eq 3 ]]; then
	sep="$2"
	parser_path="$3"
fi

# default values for the separator and the parser path
: ${sep:='='}
: ${parser_path:='./parse_conf.awk'}

declare $(./$parser_path -F$sep $1)
