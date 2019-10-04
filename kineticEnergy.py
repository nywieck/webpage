import math

def main():
    """
    Main function for different modules including: user inputs, time calculation, velocity calculation, kinetic energy calculation, and result ouput.
    """
    height_ft, mass_lb, height_m, mass_kg = get_inputs()
    time = calc_time(height_m)
    velocity_avg = calc_velocity(height_m,time)
    ke = calc_ke(mass_kg, velocity_avg)
    display(height_ft, mass_lb, ke)

def get_inputs():
    """
    Function to get starting drop height (ft) and object mass (lbs) from user, and also convert height (m) and mass (kg).
    Returns all 4 values.
    """
    print("Hello, user! This program will calculate the kinetic energy in joules (J) of a falling object just as it reaches the ground")
    print("given a starting height in feet (ft), and the mass of the object in pounds (lbs).")
    height_ft = float(input("First, enter drop height (ft): "))
    mass_lb = float(input("Next, enter mass of the object (lbs): "))
    height_m = height_ft * .3048
    mass_kg = mass_lb / 2.2046
    return(height_ft, mass_lb, height_m, mass_kg)

def calc_time(height_m):
    """
    Function to calculate time it takes for the object to reach the ground from the input height specified by the user.
    Parameter - height in meters.
    Return - time it takes for the object to reach the ground from the given height.
    """
    time = math.sqrt((2*height_m)/9.8)
    return(time)

def calc_velocity(height_m,time):
    """
    Function to calculate average velocity of the object from the time it's dropped to the time it reaches the ground.
    Parameters - height in meters, time it takes for the object to reach the ground from the given height.
    Return - average velocity of the object from when it's dropped until it reaches the ground.
    """
    velocity_avg = height_m / time
    return(velocity_avg)

def calc_ke(mass_kg,velocity_avg):
    """
    Function to calculate kinetic energy of the object at the time it hits the ground with drop height and object mass specified by user.
    Parameters - mass of the object in kilograms, average velocity of the object from when it's dropped until it reaches the ground.
    Return - kinetic energy of the object at the time it hits the ground with drop height and object mass specified by user.
    """
    ke = (1/2)*mass_kg*(velocity_avg**2)
    return(ke)

def display(height_ft,mass_lb,ke):
    """
    Function to display the resulting calculated kinetic energy.
    Parameters - drop height (ft) and object mass (lbs) initially input by user, and resulting kinetic energy.
    """
    print("The kinetic energy is:",ke,"J for a(n)",mass_lb,"lb object dropped from",height_ft,"ft high, just as it reaches the ground.")

#calls the main function
if __name__ == "__main__":
    main()
