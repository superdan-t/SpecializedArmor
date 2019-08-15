class RecipeKey:
    def __init__(self, type, abbreviation, internal_name):
        if not (type == 'ore_dict' or type == 'item'):
            print('Unknown type: ' + type)
        self.abbrev = abbreviation
        self.type = type
        self.internal_name = internal_name
        
        
    def get_first_line(self):
        if self.type == 'item':
            return '"item": "' + self.internal_name + '"'
        elif self.type == 'ore_dict':
            return '"type": "forge:ore_dict",'
            
    
    def two_line_key(self):
        if self.type == 'ore_dict':
            return True
        else:
            return False
            
            
    def get_second_line(self):
        if self.type == 'ore_dict':
            return '"ore": "' + self.internal_name + '"'