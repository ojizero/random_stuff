#! /bin/sh


##############################################################
##	Wrapper to invoke parse_conf.awk and pass it to declare	##
##	effectively creating shell variables for each option in	##
##	the config file and set it to it's value				##
##############################################################


parser_path='./parse_conf.awk'
declare $(./$parser_path $1)
