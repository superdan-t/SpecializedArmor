'''
I got tired of making a thousand JSON files because it was all mindlessly boring and repetitive...
But then I connected the dots. Anything that is repetitive is PERFECT for automation with a script!
This doesn't work on anything complicated, but for a basic item or block, it will take care of you.
'''

import resource_util
import recipe_key

# Assumes that this Python file is in the workspace root, but you can override this
assets_directory = "src/main/resources/assets/"

# This is used to locate your assets folder
mod_id = "sdspar"

util = resource_util.ResourceUtil(mod_id, dir=assets_directory)

def segment_line(line, split=' '):
    segments = []
    this_segment = ""
    for char in line:
        if char == split:
            segments.append(this_segment)
            this_segment = ""
        else:
            this_segment = this_segment + char
    segments.append(this_segment)
    return segments


print("Dan's Simple Resource Manager")

repeat = True

while repeat:
    
    line_in = input(">>| ")

    segmented_line = segment_line(line_in)

    cmd = segmented_line[0]

    if cmd == "help" or cmd == "?":

        print("Help for resource manager:")
        print("lang (add/remove) (block/item/itemgroup) (Internal Name) [Display Name]")
        print("\t- Add or remove an entry from the language file.")
        print("lang change (lang)")
        print("\t- Change the active language file. Default is en_US")
        print("lang order")
        print("\t- Put the language file in alphebetical order.")
        print("item (Internal Name)")
        print("\t- Generate necessary JSON files for an item")
        print("crafting")
        print("\t- Make a crafing recipe. No parameters, as it is a utility.")
        print("block (Internal Name)")
        print("\t- Generate necessary JSON files for a block")
        print("quit")
        print("\t- Close the utility")
        
    elif cmd == "lang":
        type = ""
        internal_name = ""
        try:
            if (segmented_line[1] != "order"):
                type = segmented_line[2]
            if (segmented_line[1] == "remove" or segmented_line[1] == "add"):
                internal_name = segmented_line[3]
        except IndexError:
            print("Too few arguments for selection.")
        if segmented_line[1] == "add":
            display_name = ""
            for item in segmented_line[4:len(segmented_line)]:
                if display_name == "":
                    display_name = display_name + item
                else:
                    display_name = display_name + " " + item
            util.add_lang_entry(type, internal_name, display_name)
        elif segmented_line[1] == "remove":
            util.remove_lang_entry(type, internal_name)
        elif segmented_line[1] == "change":
            util.default_lang = type
        elif segmented_line[1] == "order":
            util.order_lang_file

    elif cmd == "block":
        
        internal_name = segmented_line[1]
        
        util.make_item_block_json(internal_name)
        util.make_blockstate_json(internal_name)
        util.make_block_json(internal_name)
        
    elif cmd == "item":
        
        internal_name = segmented_line[1]
        
        util.make_item_json(internal_name)
        
    elif cmd == "quit":
        
        repeat = False
        
    elif cmd == "crafting":
        name = input("Name of recipe: ")
        
        print("Type the pattern, being sure to keep consistent row length using spaces. Submit a blank line if the recipe doesn't use all three rows.")
        
        pattern = []
        
        pattern.append(input("Row 1: "))
        
        if len(pattern[0]) == 0:
            print("The first row cannot be blank.")
            pattern.replace(0, input("Row 1: "))
            if len(pattern[0] == 0):
                quit()
            
        entry = input("Row 2: ")
        if len(entry) != 0:
            pattern.append(entry)
            entry = input("Row 3: ")
            if len(entry) != 0:
                pattern.append(entry)
        
        keys = []
        
        print("Indicate the keys. Submit an empty line when you are done.")
        
        entry = input("Character in key: ")
        while entry != "":
            abbrev = ""
            abbrev = entry[0]
            entry = input("Corresponds to (1) Block/Item OR (2) Ore Dictionary? ")
            type = ""
            if entry == "1":
                type = "item"
                entry = input("Internal name (including mod id) of block/item: ")
            else:
                type = "ore_dict"
                entry = input("Ore dictionary name: ")
            keys.append(recipe_key.RecipeKey(type, abbrev, entry))
            print("Done with key.")
            entry = input("Character in key: ")
            
        result = input("Resulting block/item: ")
        amount = int(input("Result amount: "))
        
        util.make_shaped_recipe(name, pattern, keys, result, amount=amount)
        
    else:
        
        print("Unknown command. Type 'help' for help.");
        