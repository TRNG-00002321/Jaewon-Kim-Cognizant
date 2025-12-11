class StringCalculator:
    """Calculator that adds numbers from a string."""
    
    def add(self, numbers: str) -> int:
        """
        Add numbers from a delimited string.
        
        Rules:
        - Empty string returns 0
        - Single number returns that number
        - Numbers can be delimited by comma or newline
        - Custom delimiter: "//[delimiter]\n[numbers]"
        - Numbers > 1000 are ignored
        - Negative numbers raise ValueError
        """
        if not numbers:
            return 0
        
        delimiter = ","
        
        # Check for custom delimiter
        if numbers.startswith("//"):
            delimiter_end = numbers.index("\n")
            delimiter = numbers[2:delimiter_end]
            numbers = numbers[delimiter_end + 1:]
        
        # Replace newlines with delimiter
        numbers = numbers.replace("\n", delimiter)
        
        # Parse numbers
        num_list = [int(n) for n in numbers.split(delimiter) if n]
        
        # Check for negatives
        negatives = [n for n in num_list if n < 0]
        if negatives:
            raise ValueError(f"negatives not allowed: {negatives}")
        
        # Filter and sum (ignore > 1000)
        return sum(n for n in num_list if n <= 1000)