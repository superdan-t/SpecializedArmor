class ResourceUtil:
    def __init__(self, mod_id, dir="src/main/resources/assets/", log=True, default_language="en_US"):
        self.log = log
        self.mod_id = mod_id
        self.assets_directory = dir
        self.default_lang=default_language
        if self.assets_directory[len(self.assets_directory) - 1] != "/":
            self.assets_directory = self.assets_directory + "/"
        self.mod_assets_directory = self.assets_directory + self.mod_id + "/"
        
    
    def make_item_json(self, internal_name):
        item_json_file = open(self.mod_assets_directory + "models/item/" + internal_name + ".json", "w")
        print('{', file=item_json_file)
        print('\t"parent": "item/generated",', file=item_json_file)
        print('\t"textures": {', file=item_json_file)
        print('\t\t"layer0": "' + self.mod_id + ':items/' + internal_name + '"', file=item_json_file)
        print('\t}', file=item_json_file)
        print('}', file=item_json_file)
        item_json_file.close();
        if self.log:
            print("Created JSON file for item " + internal_name)
    
    
    def make_blockstate_json(self, internal_name):
        blockstate_json_file = open(self.mod_assets_directory + "blockstates/" + internal_name + ".json", "w")
        print('{', file=blockstate_json_file)
        print('\t"variants": {', file=blockstate_json_file)
        print('\t\t"normal": { "model": "' + self.mod_id + ':' + internal_name + '" }', file=blockstate_json_file)
        print('\t}', file=blockstate_json_file)
        print('}', file=blockstate_json_file)
        blockstate_json_file.close()
        if self.log:
            print("Created JSON file for blockstate " + internal_name)
            
            
    def make_block_json(self, internal_name):
        model_json_file = open(self.mod_assets_directory + "models/block/" + internal_name + ".json", "w")
        print('{', file=model_json_file)
        print('\t"parent": "block/cube_all",', file=model_json_file)
        print('\t"textures": {', file=model_json_file)
        print('\t\t"all": "' + self.mod_id + ':blocks/' + internal_name + '"', file=model_json_file)
        print('\t}', file=model_json_file)
        print('}', file=model_json_file)
        model_json_file.close();
        if self.log:
            print("Created JSON file for block " + internal_name)
     
     
    def make_item_block_json(self, internal_name):
        item_json_file = open(self.mod_assets_directory + "models/item/" + internal_name + ".json", "w")
        print('{', file=item_json_file)
        print('\t"parent": "' + self.mod_id + ":" + "block/" + internal_name + '"', file=item_json_file)
        print('}', file=item_json_file)
        item_json_file.close();
        if self.log:
            print("Created JSON file for ItemBlock " + internal_name)
            
            
    def order_lang_file(self, lang=""):
        if lang == "":
            lang = self.default_lang
        lang_file = open(self.mod_assets_directory + "lang/" + lang + ".lang", "r")
        lang_file_text = lang_file.read()
        lang_file.close()
        
        lang_entries_list = []
        
        current_line = ""
        for char in lang_file_text:
            if char == '\n':
                lang_entries_list.append(current_line)
                current_line = ""
            elif char != '\r':
                current_line = current_line + char
        
        lang_entries_list.sort()
        
        lang_file = open(self.mod_assets_directory + "lang/" + lang + ".lang", "w")
        
        for entry in lang_entries_list:
            print(entry, file=lang_file)
            
        lang_file.close()
        
        if self.log:
            print("Ordered the language file for " + lang)
        
        
    def remove_lang_entry(self, type, internal_name, lang=""):
        if lang == "":
            lang = self.default_lang
        type = str.lower(type)
        if type == "block":
            type = "tile"
        
        if not (type == "tile" or type == "item" or type == "itemgroup"):
            if self.log:
                print("Invalid entry type.")
            return
        
        constructor = type + "." + self.mod_id + "." + internal_name
        
        if type == "itemgroup":
            constructor = constructor + "="
        else:
            constuctor = constructor + ".name="
            
        lang_file = open(self.mod_assets_directory + "lang/" + lang + ".lang", "r")
        lang_file_text = lang_file.read()
        lang_file.close()
        
        lang_entries_list = []
        
        current_line = ""
        for char in lang_file_text:
            if char == '\n':
                if constuctor not in current_line:
                    lang_entries_list.append(current_line)
                current_line = ""
            elif char != '\r':
                current_line = current_line + char
        
        lang_entries_list.sort()
        
        lang_file = open(self.mod_assets_directory + "lang/" + lang + ".lang", "w")
        
        for entry in lang_entries_list:
            print(entry, file=lang_file)
            
        lang_file.close()
        
        if self.log:
            print("Removed any existing entries for " + internal_name + " from " + lang)
            
            
    def add_lang_entry(self, type, internal_name, display_name, lang=""):
        if lang == "":
            lang = self.default_lang
        type = str.lower(type)
        if type == "block":
            type = "tile"
        
        if not (type == "tile" or type == "item" or type == "itemgroup"):
            if self.log:
                print("Invalid entry type.")
            return
        
        self.remove_lang_entry(type, internal_name, lang=lang)
        
        if self.log:
            print("Adding new entry...")
        
        constructor = type + "." + self.mod_id + "." + internal_name
        
        if type == "itemgroup":
            constructor = constructor + "="
        else:
            constructor = constructor + ".name="
            
        lang_file = open(self.mod_assets_directory + "lang/" + lang + ".lang", "r")
        lang_file_text = lang_file.read()
        lang_file.close()
        print_extra_line_ending = False
        if lang_file_text[len(lang_file_text) - 1] != '\n':
            print_extra_line_ending = True
            
        lang_file = open(self.mod_assets_directory + "lang/" + lang + ".lang", "a")
        
        if print_extra_line_ending:
            print("", file=lang_file)
        
        print(constructor + display_name, file=lang_file)
        
        lang_file.close()
        
        self.order_lang_file(lang=lang)
            
            
    def make_shaped_recipe(self, name, pattern, keys, result, amount=1):
        recipe_file = open(self.mod_assets_directory + "recipes/" + name + ".json", "w")
        
        print('{', file=recipe_file)
        print('\t"type": "minecraft:crafting_shaped",', file=recipe_file)
        print('\t"pattern":', file=recipe_file)
        print('\t[', file=recipe_file)
        
        for num in range(len(pattern)):
            if num == len(pattern) - 1:
                print('\t\t"' + pattern[num] + '"', file=recipe_file)
            else:
                print('\t\t"' + pattern[num] + '",', file=recipe_file)
                
        print('\t],', file=recipe_file)
        print('\t"key": {', file=recipe_file)
                
        for num in range(len(keys)):
            print('\t\t"' + keys[num].abbrev + '": {', file=recipe_file)
            print('\t\t\t' + keys[num].get_first_line(), file=recipe_file)
            if keys[num].two_line_key():
                print('\t\t\t' + keys[num].get_second_line(), file=recipe_file)
            
            if num == len(keys) - 1:
                print('\t\t}', file=recipe_file)
            else:
                print('\t\t},', file=recipe_file)
            
        print('\t},', file=recipe_file)
        
        print('\t"result": {', file=recipe_file)
        print('\t\t"item": "' + result + '",', file=recipe_file)
        print('\t\t"count": ' + str(amount), file=recipe_file)
        print('\t}', file=recipe_file)
        print('}', file=recipe_file)
        
        recipe_file.close()
        
        if self.log:
            print("Created recipe JSON.")
        
