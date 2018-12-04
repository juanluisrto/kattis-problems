import sys

def logic(P, X0, X1):
	return 0 if X0 is P else -1 + 2*(X1 is P)

input = sys.stdin.readline()
answer = []
answer.append(input[1:].count('D')*2 + logic('U',input[0],input[1]))
answer.append(input[1:].count('U')*2 + logic('D',input[0],input[1]))
answer.append(input.count('UD') + input.count('DU'))
for i in answer:
	sys.stdout.write(str(i) + '\n') 


