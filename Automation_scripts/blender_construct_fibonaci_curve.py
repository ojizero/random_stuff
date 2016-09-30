import bpy as blender
from random import random


def fibo_spiral (limit_count=8, fill=False, vary_on_Z=False, circle_else_cylinder=True, bevel_if_3d=True):
	# def fibo_seq (limit_count):
	#   prev, ppre, fseq, num = 1, 1, [1, 1], 2
	#   
	#   while (num < limit_count):
	#   	a = prev + ppre
	#   	fseq.append(a)
	#   	ppre = prev
	#   	prev = a
	#   	num += 1
	#   	
	#   return fseq
	
	XY, Z = [0, 0], 0
	sign, ch, count = 1, 0, 0
	current, previous = 1, 0
	
	fill_para = 'fill_type' if circle_else_cylinder else 'end_fill_type'
	fill_type = 'NGON' if fill else 'NOTHING'
	params = {
		'radius'			:   0,
		'depth' 			:   0.1,
		'location'  		:   (0,0,0),
		fill_para   		:   fill_type,
		'enter_editmode'	:   True
	}

	# fibo = fibo_seq(limit_count+1)
	
	for iter in range(limit_count):
		center = XY.copy()
		center.append(Z)
		
		params['radius'] = current+previous
		params['location'] = center
		
		blender.ops.mesh.primitive_cylinder_add(**params)
		if bevel_if_3d:
			blender.ops.mesh.bevel(offset=0.05)
		
		XY[ch] += (sign * current)
		ch = ~ch	# each iteration change either X or Y
		
		if vary_on_Z:
			Z -= random()
		
		count += 1
		if count == 2:
			sign *= -1
			count = 0
		
		current, previous = current+previous, current


fibo_spiral(limit_count=16, vary_on_Z=True, fill=True, circle_else_cylinder=False, bevel_if_3d=True)