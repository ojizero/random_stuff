# Automation Scripts
These are a bunch of random small scripts that I made to help me automate some routine (somewhat boring) tasks.

## update_mirrors
is a Shellscript that updates the mirrorlist used by Pacman on ArchLinux, it's quite a simple task but it's boring one, having to use two of the same commands everytime I notice that the mirrorlist was updated. It also creates a backup of the old mirrorlist in case things go haywire!!

## fill_missing.py
I'm taking the practical training course in my university, and we are required to report each day we're at work on our universit y academic portal (Ritaj), but the online report tool was opened about 10 days late, and I am just not gonna go on filling those days one by one, so this script uses the Selenium tool to automatically fill those 10 days, since all share almost identical details.

## parse_conf.awk
I was wondering on how to parse through config files quickly while ignoring comments, whitespaces, and the such, so I decided to give it a try lol, this script prints out pairs of the options of a config file paired with their values (separated by '='), the **parse_conf.sh** script wraps this script, and calles it inside `declare $( ... )`. This wrapper would create Shell variables for each option in the config file and set it to the value in assigned by the config. It splits options from values using '=' as a default delimeter, or if passed a second argument would use it as a delimeter. A third optional arguman can be used to give it the path to the parser, the default is `'./parse_conf.awk'`.

## hide_icons
Remove icons from app launcher based on a subdirectory mimicing the `/usr/share/applications`. Adds `Hidden=True` options to affected `.desktop` files.

## blender_construct_fibonaci_curve.py
I wanted to draw a wallpaper, and I wanted to use the Fibonaci spiral as basis for it, but making a Fbonaci spiral is very repetitive so I ended up automating it ith this script.
