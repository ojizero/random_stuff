#!/usr/bin/env python3

from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select

import time
import datetime

"""
This script was built to automate the fill of the practical training I'm taking course with,
since the online report was opened after about 10 days of us training, I sought to automate
the process instead of doing the boring task of inputing 10 days of the same info !!
"""

try:
	# open firefox
	browser = webdriver.Firefox()
	# login to ritaj and open training page
	browser.get('https://ritaj.birzeit.edu/student/training/report')
	# assert 'Ritaj' in browser.title

	# take crendentials for login
	user = input('Insert username > ').strip()
	psrd = input('Insert password > ').strip()
	# login
	browser.find_element_by_name('username').send_keys(user)
	browser.find_element_by_name('password').send_keys(psrd)
	browser.find_element_by_name('formbutton:ok').click()
	# wait for login to complete
	time.sleep(3)

	# function to fill the data in the website
	def fill(tid, day_str, month_str, from_hour, from_minute, to_hour, to_minute, details):
		# select ID
		Select(browser.find_element_by_name('training_id')).select_by_value(tid)
		# select day
		Select(browser.find_element_by_name('training_date.day')).select_by_value(day_str)
		Select(browser.find_element_by_name('training_date.month')).select_by_value(month_str)
		# select start time
		Select(browser.find_element_by_name('start_time.hours')).select_by_value(from_hour)
		Select(browser.find_element_by_name('start_time.minutes')).select_by_value(from_minute)
		# select end time
		Select(browser.find_element_by_name('ending_time.hours')).select_by_value(to_hour)
		Select(browser.find_element_by_name('ending_time.minutes')).select_by_value(to_minute)
		# description/details
		browser.find_element_by_name('work_description').send_keys(details)
		# submit
		browser.find_element_by_name('formbutton:ok').click()
		# wait a sec
		time.sleep(1)

	## fill in June
	for from_day in range(21, 31):
		if datetime.datetime(year=2016, month=6, day=from_day).weekday() in (4, 5):
			# skip fridays and saturdays (weekend)
			continue
		else:
			fill(tid='3384', day_str=str(from_day), month_str='6', from_hour='9', from_minute='10', to_hour='14', to_minute='45', details='Introductary CCNA courses.')

	## fill in July (one day)
	fill(tid='3384', day_str='3', month_str='7', from_hour='9', from_minute='15', to_hour='14', to_minute='10', details='Introductary CCNA courses.')

except Exception as e:
	print('Double check the code !!')
finally:
	browser.quit()
	exit(-1)
