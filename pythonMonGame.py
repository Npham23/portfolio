"""
"I pledge that on all academic work that I submit,
I will neither give nor receive unauthorized aid,
nor will I present another person's work as my own"
Nathan Pham
4/30/2020
Python Project: Pokemon Battle Simulator
Links for Reference: https://www.youtube.com/watch?v=Pbs6jQZrZA4
"""

import logging
import random #We are importing multiple libraries
import sys
import time


logging.basicConfig(filename=r"C:\pythonclass\log\Nathan.log",filemode='w')
# You must use logging and need to point to C:\pythonclass\log\. The file name must be studentfirstname.log
logger=logging.getLogger()
logger.setLevel(logging.DEBUG)

#a buffer is a portion in memory
#this can make your program lag your computer
#stdout will print what string yuou give it
def delay(s):#function called delay to make it more of a realistic pokemon
    for c in s:
        sys.stdout.write(c)
        sys.stdout.flush() # pushes out the buffered 
        time.sleep(0.03) #sleep for how many seconds
        
print("Choose your Pokemon! \n[1] Charizard\n[2] Blastoise\n[3] Venusaur")


class Pokemon:
    def __init__(self, name, types, move, EVs):#What happens is that, I have already given the inputs in the program
        self.name = name #it will grab all the data given and give these variables the values
        self.types = types
        self.move = move
        self.health = EVs['HP']
        self.attack = EVs['ATTACK']
        self.spattack = EVs['SP. ATTACK']
        self.defense = EVs['DEFENSE']
        self.spdefense = EVs['SP. DEFENSE']
        self.bar = 20
        self.poison = False #Set to false so all pokemons will not be burning, see below for more in the pokemon battle
        self.burn = False #Set to false so all pokemons will not be poisoned, see below for more
        

    
    def fight(self, Pokemon2nd):
        delay("\nGary wants to battle you!\n")
        print("\n     Pokemon Battle     \n")#we are just printing out the data that it was given through its class
        print(f"{self.name}")
        print("LV: 100 / TYPE:", self.types)
        print("ATTACK: ", self.attack," / ", "SP. ATTACK: ", self.spattack)
        print("DEFENSE: ", self.defense, " / ", "SP. DEFENSE: ", self.spdefense)
        print("\n           VS\n")
        print(f"{Pokemon2nd.name}")
        print("LV: 100 / TYPE:", Pokemon2nd.types)
        print("ATTACK: ", Pokemon2nd.attack," / ", "SP. ATTACK: ", Pokemon2nd.spattack)
        print("DEFENSE: ", Pokemon2nd.defense, " / ", "SP. DEFENSE: ", Pokemon2nd.spdefense)
        time.sleep(2)
        global r #needed to use r in the program, else it will give an error
        global count, count2, count3, count4
        
    ###############################################################
                             #Modifiers
    ###############################################################
                             
        def typeAttack(): #Increase attack damage if it is a type advantage
            self.attack *= 2
            self.spattack *= 2
            Pokemon2nd.attack *= 2
            Pokemon2nd.spattack *= 2

        def typeDefense(): #Increase defense if it is a type advantage
            self.defense *= 2
            self.spdefense *= 2
            Pokemon2nd.defense *= 2
            Pokemon2nd.spdefense *= 2

        def ResetDefense():
            self.defense /= 2
            self.spdefense /= 2
            Pokemon2nd.defense /= 2
            Pokemon2nd.spdefense /= 2

        def ResetAttack():
            self.attack /= 2
            self.spattack /= 2
            Pokemon2nd.attack /= 2
            Pokemon2nd.spattack /= 2

        #Game reference https://bulbapedia.bulbagarden.net/wiki/Damage but I am modifying it a bit
        #Player is attacking
        def playerAttack():
            Pokemon2nd.health -= ((((200 / 5) + 2) * (self.attack / Pokemon2nd.defense)) + 2)
        
        def playerSpAttack():
            Pokemon2nd.health -= ((((200 / 5) + 2) * (self.spattack / Pokemon2nd.spdefense)) + 2)

        #Opponent is attacking
        def Pokemon2ndAttack():
             self.health -= ((((200 / 5) + 2) * (Pokemon2nd.attack / self.defense)) + 2)

        def Pokemon2ndSpAttack():
             self.health -= ((((200 / 5) + 2) * (Pokemon2nd.spattack / self.spdefense)) + 2)
        

    #Start Pokemon Battle
        while((self.health > 0) and (Pokemon2nd.health > 0)):
            if (Pokemon2nd.poison):#This will only allow one status aliment per pokemon
                delay(f"Foes' {Pokemon2nd.name} has loss hp due to poison! \n")
                logger.info("\n Foes is losing hp from poison\n")
                self.health -= (360 * (1 / 16))
            elif (self.burn):
                self.health -= (self.health * (1 / 8))
                logger.info("\n Foes is losing hp from burning\n")
                delay(f"Foes's {Pokemon2nd.name} has loss hp due to being burned! \n")
                
            if (self.poison):
                delay(f"Your {self.name} has loss hp due to poison! \n")
                logger.info("\n Player is losing hp from poison\n")
                Pokemon2nd.health -= (360 * (1 / 16))
            elif (Pokemon2nd.burn):
                delay(f"Your {Pokemon2nd.name} has loss hp due to being burned! \n")
                logger.info("\n Player is losing hp from burning\n")
                Pokemon2nd.health -= (self.health * (1 / 8))
          
            print(f"{self.name}\t\tHP\t{self.health}")
            print(f"{Pokemon2nd.name}\t\tHP\t{Pokemon2nd.health}\n")
            print(f" Go {self.name}!")
            for i, x in enumerate(self.move):
                print(f"{i+1}.", x)
            index = int(input("What will " + self.name + " do? "))
            delay(f"\n{self.name} use {self.move[index-1]}!")
            time.sleep(1)
            
            """Use this link for more info of the pokemon moves, this is
            not about the coding but more indepth of the game mechanics
            https://bulbapedia.bulbagarden.net/wiki/Main_Page"""
            #we are running an if and else statements
            if (user == '1'):#Charizard
                if (index == 1): #Fire Blast
                    logger.info("\n Player chosen Fire blast\n")
                    self.spattack *= 1.1#Real attack modifier, adjusting power
                    if (computer == 2):#Blastoise is steel and water, so typing has negated to normal damage
                        playerSpAttack()
                    elif (computer == 3):#type advantage
                        typeAttack()
                        playerSpAttack()
                        ResetAttack()
                        logger.info("\n It was very effective!\n")
                        delay(s1) #print string "It's Very Effective!"
                    self.spattack /= 1.1
                    r = random.randint(1, 10)
                    if (r == 1):# 10% has to give burn aliment
                        Pokemon2nd.burn = True
                        logger.info("\n Foe has been burned\n")
                        delay(f" Foes' {Pokemon2nd.name} has been burned! \n")
                elif (index == 2): #Focus Blast
                    logger.info("\n Player chosen Focus blast\n")
                    self.spattack *= 1.2
                    if (computer == 2):
                        typeAttack()
                        playerSpAttack()
                        ResetAttack()
                        logger.info("\n It was very effective!\n")
                        delay(s1) #print string "It's Very Effective!"
                    elif (computer == 3):
                         playerSpAttack()
                    self.spattack /= 1.2
                    r = random.randint(1, 10)#10% to happen
                    if (r == 1):
                        logger.info("\n Foes has it's sp. defense lowered\n")
                        Pokemon2nd.spdefense *= 0.8
                        delay(f"Foes' {Pokemon2nd.name} Sp. Def has been lowered! \n")
                elif (index == 3): #Solar Beam, my own modified version
                    logger.info("\n Player chosen Solar beam\n")
                    count += 1# must be chosen twice
                    if (count == 1):
                        logger.info("\n 1st charge for solar beam\n")
                        delay(f" {self.name} is building up energy. \n")
                    elif (count == 2):
                        logger.info("\n 2nd charge, player release charge\n")
                        self.spattack *= 2
                        if (computer == 3): # Grass vs Grass/Poison reduces by 1/4
                            typeDefense()
                            typeDefense()
                            playerSpAttack()
                            ResetDefense()
                            ResetDefense()
                            delay(f" {self.name} has dealt a devestating blow! ")
                            logger.info("\n It NOT very effective...\n")
                            delay(s2)
                            print("\n")
                        elif (computer == 2):
                            playerSpAttack()
                            delay(f" {self.name} has dealt a devestating blow! \n")#to confirm for damage
                        count -= 2
                        logger.info("\n Resetting charges for player\n")
                        self.spattack /= 2
                elif (index == 4): #Dragon Pulse
                    logger.info("\n Player has chosen Dragon pulse\n")
                    self.spattack *= 0.85
                    playerSpAttack()
                    self.spattack /= 0.85
            elif (user == '2'):#Blastoise
                if (index == 1): #Scald
                    logger.info("\n Player has chosen Scald\n")
                    self.spattack *= 0.8
                    if (computer == 1):#water vs fire
                        typeAttack()
                        playerSpAttack()
                        ResetAttack()
                        logger.info("\n It was very effective!\n")
                        delay(s1) #very effective
                    elif (computer == 3):# burn only affects venusaur because charizard is a fire type
                        typeDefense()#plus venusaur is not a fire type
                        playerSpAttack()
                        ResetDefense()
                        logger.info("\n It NOT very effective...\n")
                        delay(s2)#not very effecttive
                        r = random.randint(1, 10)
                        if (r == 1):# 10%
                            logger.info("\n Foes has been burned\n")
                            Pokemon2nd.burn = True
                            delay(f" Foes' {Pokemon2nd.name} has been burned! \n")
                    self.spattack /= 0.8
                elif (index == 2): #Earthquake
                    logger.info("\n Player has chosen Earthquake")
                    self.attack *= 1
                    if (computer == 1):
                        delay(" But it missed! Charizard is Flying above ground!")
                        logger.info("\n Too bad, Charizard is flying, so HP was negated\n")
                    elif (computer == 3):
                        playerAttack()
                    self.attack /= 1 #Reset like always
                elif (index == 3): #Skull Bash is a normal move
                    logger.info("\n Player has chosen Skull bash\n")
                    self.spattack *= 1.3
                    playerAttack()
                    self.spattack /= 1.3
                    self.defense *= 1.2
                    self.spdefense *= 1.2 #new modifications
                    logger.info("\n Blastoise has raise its sp. def and def")
                    delay(f" {self.name} has raise its Defense and Sp. Defense! \n")
                elif (index == 4): #Dark Pulse
                    logger.info("\n Player has chosen Dark pulse\n")
                    self.spattack *= 0.8
                    playerSpAttack()
                    self.spattack /= 0.8
            elif (user == '3'):#Venusaur
                if (index == 1): #Solar Beam
                    logger.info("\n Player has chosen Solar beam\n")
                    count2 += 1# must be chosen twice
                    if (count2 == 1):
                        logger.info("\n Charge 1")
                        delay(f" {self.name} is building up energy. \n")
                    elif (count2 == 2):
                        logger.info("\n Charge 2, player releases its charges\n")
                        self.spattack *= 2
                        if (computer == 1): # Grass vs Fire/Flying reduces by 1/4
                            typeDefense()
                            typeDefense()
                            playerSpAttack()
                            ResetDefense()
                            ResetDefense()
                            delay(f" {self.name} has dealt a devestating blow! ")
                            delay(s2)
                            logger.info("\n It NOT very effective...\n")
                            print("\n")
                        elif (computer == 2):
                            playerSpAttack()
                            delay(f" {self.name} has dealt a devestating blow! ")#to confirm for damage
                        count2 -= 2
                        logger.info("\n Reseting charges for player")
                        self.spattack /= 2
                elif (index == 2): #Earth Power
                    logger.info("\n Player has chosen Earth power")
                    self.spattack *= 0.9
                    if (computer == 1):
                        logger.info("\n Too bad, Charizard is flying so HP was negated\n")
                        delay(" But it missed! Charizard is Flying above ground!")
                    elif (computer == 2):#Ground vs Steel/Water
                        typeAttack()
                        playerSpAttack()
                        ResetAttack()
                        logger.info("\n It was very effective!\n")
                        delay(s1)
                        r = random.randint(1, 10)#10% to happen and plus Charizard is flying so it won't effct
                        if (r == 1):#so this will only effect blastoise
                            Pokemon2nd.spdefense *= 0.8
                            logger.info("\n Foes' Pokemon Sp. def has been lowered\n")
                            delay(f" Foes' {Pokemon2nd.name} Sp. Def has been lowered! \n")
                    self.spattack /= 0.9
                elif (index == 3): #Sludge Bomb
                    logger.info("\n Player has chosen Sludge bomb\n")
                    self.spattack *= 0.9
                    if (computer == 1):
                        playerAttack()
                        r = random.randint(1, 10)# 30% chance to work
                        if (r <= 3):# remember steel types are resistant to poison
                            Pokemon2nd.poison = True
                            logger.info("\n Foes' has been poison\n")
                            delay(f"Foes' {Pokemon2nd.name} has been poisoned! \n")
                    elif (computer == 2):
                        logger.info("\n Blastoise has resisted the poison\n")
                        delay(f" {Pokemon2nd.name} has resisted the poisonous substances!\n")
                    self.spattack /= 0.9
                elif (index == 4): #Giga Drain my own move, because it will need a buff
                    logger.info("\n Player has chosen tera drain\n")
                    self.spattack *= 1.2
                    typeDefense()
                    playerSpAttack()
                    self.spattack /= 1.2
                    logger.info("\n It NOT very effective...\n")
                    delay(s2)
                    print("\n")
                    self.health += ((((200 / 5) + 2) * (self.spattack / Pokemon2nd.spdefense)) + 2) # this is healing itself
                    ResetDefense()
                else:
                    delay("Gary: You can't run from battle!\n")#probably have mistyped a key
                            
            time.sleep(1)

            print(f"\n{self.name}\t\tHP\t{self.health}")
            print(f"{Pokemon2nd.name}\t\tHP\t{Pokemon2nd.health}\n")
            time.sleep(.5)

            if Pokemon2nd.health <= 0:
                delay("\n" + Pokemon2nd.name + " fainted!")
                logger.info("\n Foes' pokemon has fainted\n")
                delay("\n No! \nThat can't be!\nYou beat my best!\nAfter all that work\nMy reign is over! This isn't not fair >:(")
                delay("\nYou got $6435 for winning!\n")
                logger.info("\n Player wins!")
                break
            
    #################################################################################                
                            #Pokemon 2 turn to attack
    #################################################################################
            
            print(f"Gary: Go {Pokemon2nd.name}!")
            index = random.randint(1, 4)
            delay(f"\nGary: {Pokemon2nd.name} use {Pokemon2nd.move[index-1]}!")
            time.sleep(1)

            if (computer == 1):#Charizard
                if (index == 1): #Fire Blast
                    logger.info("\n Foe has chosen Scald\n")
                    Pokemon2nd.spattack *= 1.1#Real attack modifier, adjusting power
                    if (user == '2'):#Blastoise is steel and water, so typing has negated to normal damage
                        playerSpAttack()
                    elif (user == '3'):#type advantage
                        typeAttack()
                        Pokemon2ndSpAttack()
                        ResetAttack()
                        logger.info("\n It was very effective!\n")
                        delay(s1) #print string "It's Very Effective!"
                    self.spattack /= 1.1
                    r = random.randint(1, 10)
                    if (r == 1):# 10% has to give burn aliment
                        logger.info("\n Player's has been burned\n")
                        self.burn = True
                        delay(f" Your {self.name} has been burned! \n")
                elif (index == 2): #Focus Blast
                    logger.info("\n Foe has chosen Focus Blast\n")
                    Pokemon2nd.spattack *= 1.2
                    if (user == '2'):#Blastoise is steel type and fighting is great against it
                        typeAttack()
                        Pokemon2ndSpAttack()
                        ResetAttack()
                        logger.info("\n It was very effective!\n")
                        delay(s1) #print string "It's Very Effective!"
                    elif (user == '3'):
                         Pokemon2ndSpAttack()
                    Pokemon2nd.spattack /= 1.2
                    r = random.randint(1, 10)#10% to happen
                    if (r == 1):
                        self.spdefense *= 0.8
                        logger.info("\n Player's sp. def has been lowered\n")
                        delay(f" Your {self.name} Sp. Def has been lowered! \n")
                elif (index == 3): #Solar Beam, my own modified version because it felt weak
                    count3 += 1# must be chosen twice
                    logger.info("\n Foe has chosen Solar beam\n")
                    if (count3 == 1):
                        logger.info("\n Charge 1\n")
                        delay(f" {Pokemon2nd.name} is building up energy. \n")
                    elif (count3 == 2):
                        logger.info("\n Charge 2, Foe is releasing its charges\n")
                        Pokemon2nd.spattack *= 2
                        if (user == '3'): # Grass vs Grass/Poison reduces by 1/4
                            typeDefense()
                            typeDefense()
                            Pokemon2ndSpAttack()
                            ResetDefense()
                            ResetDefense()
                            delay(f" {Pokemon2nd.name} has dealt a devestating blow! ")
                            delay(s2)#not very effective
                            logger.info("\n It NOT very effective...\n")
                            print("\n")
                        elif (user == '2'):
                            Pokemon2ndSpAttack()
                            delay(f" {Pokemon2nd.name} has dealt a devestating blow! \n")#to confirm for damage
                        count3 -= 2#resetting count
                        logger.info("\n Resetting count\n")
                        Pokemon2nd.spattack /= 2
                elif (index == 4): #Dragon Pulse
                    logger.info("\n Foe has chosen Dragon pulse\n")
                    Pokemon2nd.spattack *= 0.85
                    Pokemon2ndSpAttack()
                    Pokemon2nd.spattack /= 0.85
            elif (computer == 2):#Blastoise
                if (index == 1): #Scald
                    logger.info("\n Foe has chosen Scald\n")
                    Pokemon2nd.spattack *= 0.8
                    if (user == '1'):#water vs fire
                        typeAttack()
                        Pokemon2ndSpAttack()
                        ResetAttack()
                        logger.info("\n It was very effective!\n")
                        delay(s1) #very effective
                    elif (user == '3'):
                        typeDefense()
                        Pokemon2ndSpAttack()
                        ResetDefense()
                        logger.info("\n It NOT very effective...\n")
                        delay(s2)#not very effecttive
                        r = random.randint(1, 10)
                        if (r == 1):# 10%
                            self.burn = True
                            logger.info("\n Player has been burned\n")
                            delay(f"Your {self.name} has been burned! \n")
                    Pokemon2nd.spattack /= 0.8
                elif (index == 2): #Earthquake
                    logger.info("\n Foe has chosen Earthquake\n")
                    Pokemon2nd.attack *= 1
                    if (user == '1'):
                        logger.info("\n Too bad. Charizard is flying so it negated damage\n")
                        delay(" But it missed! Charizard is Flying above ground!")
                    elif (user == '3'):
                        Pokemon2ndAttack()
                    Pokemon2nd.attack /= 1 #Reset like always
                elif (index == 3): #Skull Bash is a normal move
                    logger.info("\n Foe has chosen Skull bash\n")
                    Pokemon2nd.spattack *= 1.3
                    Pokemon2ndAttack()
                    Pokemon2nd.spattack /= 1.3
                    Pokemon2nd.defense *= 1.2
                    Pokemon2nd.spdefense *= 1.2 #new modifications
                    logger.info("\n Foes' Pokemon has raose its sp. def and def\n")
                    delay(f" Foes' {Pokemon2nd.name} has raise its Defense and Sp. Defense! \n")
                elif (index == 4): #Dark Pulse
                    logger.info("\n Foe has chosen Dark Pulse\n")
                    Pokemon2nd.spattack *= 0.8
                    Pokemon2ndSpAttack()
                    Pokemon2nd.spattack /= 0.8
            elif (computer == 3):#Venusaur
                if (index == 1): #Solar Beam
                    logger.info("\n Foe has chosen Solar beam\n")
                    count4 += 1# must be chosen twice
                    if (count4 == 1):
                        logger.info("\n Charges 1\n")
                        delay(f" {Pokemon2nd.name} is building up energy. \n")
                    elif (count4 == 2):
                        logger.info("\n Charges 2, foe is releasing its charges\n")
                        Pokemon2nd.spattack *= 2
                        if (user == '1'): # Grass vs Fire/Flying reduces by 1/4
                            typeDefense()
                            typeDefense()
                            Pokemon2ndSpAttack()
                            ResetDefense()
                            ResetDefense()
                            delay(f" {Pokemon2nd.name} has dealt a devestating blow! ")
                            delay(s2)
                            logger.info("\n It NOT very effective...\n")
                            print("\n")
                        elif (user == '2'):
                            Pokemon2ndSpAttack()
                            delay(f" {Pokemon2nd.name} has dealt a devestating blow! ")#to confirm for damage
                        count4 -= 2
                        logger.info("\n Resetting charges for Foe\n")
                        Pokemon2nd.spattack /= 2
                elif (index == 2): #Earth Power
                    logger.info("\n Foe has chosen Earth power\n")
                    Pokemon2nd.spattack *= 0.9
                    if (user == '1'):
                        logger.info("\n Too bad, Charizard is flying so it negated the hp\n")
                        delay(" But it missed! Charizard is Flying above ground!")
                    elif (user == '2'):#Ground vs Steel/Water
                        typeAttack()
                        Pokemon2ndSpAttack()
                        ResetAttack()
                        logger.info("\n It was very effective!\n")
                        delay(s1)
                        r = random.randint(1, 10)#10% chance to reduce special defense
                        if (r == 1):
                            logger.info("\n Player's sp. def is lowered\n")
                            self.spdefense *= 0.8
                            delay(f" Your {self.name} Sp. Def has been lowered! \n")
                    Pokemon2nd.spattack /= 0.9
                elif (index == 3): #Sludge Bomb
                    logger.info("\n Foe has chosen Sludge bomb\n")
                    Pokemon2nd.spattack *= 0.9
                    if (user == '1'):
                        Pokemon2ndAttack()
                        r = random.randint(1, 10)# 30% to poison
                        if (r <= 3):#realisticly it only effects Charizard because blastoise is a steel type Pokemon
                            self.poison = True
                            logger.info("\n Player has been poisoned\n")
                            delay(f" Your {Pokemon2nd.name} has been poisoned! \n")
                    elif (user == '2'):
                        logger.info("\n Player has resisted\n")
                        delay(" {self.name} has resisted the poisonous substances!\n")
                    Pokemon2nd.spattack /= 0.9
                elif (index == 4): #Giga Drain my own move, because it will need a buff
                    logger.info("\n Foe has chosen Tera drain\n")
                    Pokemon2nd.spattack *= 1.2
                    typeDefense()
                    Pokemon2ndSpAttack()
                    Pokemon2nd.spattack /= 1.2
                    delay(s2)
                    logger.info("\n It NOT very effective...\n")
                    print("\n")
                    Pokemon2nd.health += ((((200 / 5) + 2) * (Pokemon2nd.spattack / self.spdefense)) + 2) # this is healing itself
                    ResetDefense()

            
            time.sleep(1)
            print(f"\n{self.name}\t\tHP\t{self.health}")
            print(f"{Pokemon2nd.name}\t\tHP\t{Pokemon2nd.health}\n")
            time.sleep(.5)

            if self.health <= 0:
                logger.info("\n Player's pokemon has fainted\n")
                delay("\n" + self.name + " fainted!")
                delay("\nYou snooze, you lose!\nTry again after you stop being a third rate duelist!\n")
                logger.info("\n Player loses!\n")
                break



if __name__ == "__main__": #acts like a main function, like int main() in C++
    #These are the data input for the classes later in the coding
    Charizard = Pokemon('Charizard', 'Fire/Flying', ['Fire Blast', 'Focus Blast',
                                               'Solar Beam', 'Dragon Pulse'],
                         {'HP': 360, 'ATTACK': 293, 'DEFENSE': 280, 'SP. ATTACK': 348,
                          'SP. DEFENSE': 295})
    Blastoise = Pokemon('Blastoise', 'Water/Steel', ['Scald', 'Earthquake',
                                               'Skull Bash', 'Dark Pulse'],
                         {'HP': 360, 'ATTACK': 362, 'DEFENSE': 362, 'SP. ATTACK': 295,
                          'SP. DEFENSE': 339})
    Venusaur = Pokemon('Venusaur', 'Grass/Poison', ['Solar Beam', 'Earth Power',
                                               'Sludgebomb', 'Tera Drain'],
                         {'HP': 360, 'ATTACK': 289, 'DEFENSE': 291, 'SP. ATTACK': 328,
                          'SP. DEFENSE': 328})
    
    count = 0 # For solar beam uses only
    count2 = 0
    count3 = 0
    count4 = 0

    s1 = " It's very effective!" #strings that will be used later in the battle
    s2= " It's not very effective..."

             
    while True:#will keep repeating until user enters a valid input
        user = input("Enter: ")
        if ((user == '1') or (user == '2') or (user == '3')):
            logger.info("Player is chosing a Pokemon\n")
            break
        print("\nPlease try again")
    while True:
        computer = random.randint(1, 3)#generating a pokemon for opponent so that it won't vs the same pokemon
        if computer == 1 and user == '2':
            logger.info("Player has chosen Blastoise\n Computer has Chosen Charizard")
            break
        elif computer == 2 and user == '1':
            logger.info("Player has chosen Charizard\n Computer has Chosen Blastoise")
            break
        elif computer == 2 and user == '3':
            logger.info("Player has chosen Venusaur\n Computer has Chosen Blastoise")
            break
        elif computer == 3 and user == '1':
            logger.info("Player has chosen Charizard\n Computer has Chosen Venusaur")
            break
        elif computer == 1 and user == '3':
            logger.info("Player has chosen Venusaur\n Computer has Chosen Charizard")
            break
        elif computer == 3 and user == '2':
            logger.info("Player has chosen Blastoise\n Computer has Chosen Venusaur")
            break
        else:
            continue
        
    #setting up the gameplay
    if (user == '1'):
        if (computer == 2):
            Charizard.fight(Blastoise)
        else:
            Charizard.fight(Venusaur)
    elif (user == '2'):
        if(computer == 1):
            Blastoise.fight(Charizard)
        else:
            Blastoise.fight(Venusaur)
    else:
        if(computer == 1):
            Venusaur.fight(Charizard)
        elif (computer == 2):
            Venusaur.fight(Blastoise)




