def read(filename):
    with open(filename) as f:
        lines=f.readlines()
        return [float(line.strip()) for line in lines]
    
def calculate_total(filename):
    numbers=read(filename)
    return sum(numbers)