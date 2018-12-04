import sys

class Cup(object):
	def __init__(self, radius,color):
		self.radius = radius
		self.color = color

cup_number = int(sys.stdin.readline())
cups = []

for line in sys.stdin:
    cup_input = line.split()
    if cup_input[0].isdigit():
    	cups.append(Cup(radius=int(cup_input[0])/2., color=cup_input[1]))
    else:
    	cups.append(Cup(radius=int(cup_input[1]), color=cup_input[0]))

cups.sort(key=lambda x: x.radius)

for cup in cups:
	sys.stdout.write(cup.color + '\n')
	



