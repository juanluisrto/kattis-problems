import sys, math

input = sys.stdin.readline().split()
h = int(input[0])
v = int(input[1])
rad = v*math.pi/180 #convert to radians
sys.stdout.write(str(int(math.ceil(h/math.sin(rad)))))