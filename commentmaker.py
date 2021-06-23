# GRUPPE 21
import os

files = [x for x in os.listdir() if x[-4:] == 'java']

commend = '// GRUPPE 21\n'

for file in files:
    data = ''
    with open(file, 'r') as f:
        data = commend + f.read()
    with open(file, 'w') as f:
        f.write(data)


