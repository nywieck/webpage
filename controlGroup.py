#Exercise - Random Module - AB Experiment
#Nathaniel Wieck
#CSC 110
#6 JUN 2018

import random
def main():
    patients = ["Antonidas", "Jaina", "Malfurion", "Uther", "Maiev", "Sylvannas", "Grommash", "Rex", "Guldan", "Moose"]
    experiment, control = ab_testing(patients)
    print("Experiment group:",experiment)
    print("Control group:",control)

def ab_testing(patients):
    half = (len(patients)/2)
    pick_list = []
    experiment = []
    control = []
    eof = False
    while eof == False:
        pick = random.randint(0, len(patients)-1)
        if len(pick_list) == half:
            eof = True
        elif pick not in pick_list:
            pick_list.append(pick)
            experiment.append(patients[pick])
    for subject in patients:
        if subject not in experiment:
            control.append(subject)
    return(experiment, control)

main()
