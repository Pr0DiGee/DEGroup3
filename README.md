# DE Diner - LibGDX Kotlin Game

A fun cartoon arcade dodge & collect game where players avoid falling bad foods and collect fruits for points and power-ups.

## Features

- **5 Game Screens**: Menu, Character Select, Help, Game, and Game Over
- **10 Unique Characters**: Each with their own special abilities and funny bios
- **Power-Up System**: Shield, double points, magnet effect, and extra lives
- **Progressive Difficulty**: Game gets faster every 30 seconds, chaos mode every 60 seconds
- **Multi-Platform Input**: Keyboard controls for desktop, swipe controls for mobile
- **Sound & Music**: Full audio experience with sound effects and background music

## Characters

1. **Chef Habeeb** - "Sees opportunity in every bite." (Bonus for rare fruits)
2. **Lanre the Ladle** - "Stirs up trouble for junk food." (Faster after power-ups)
3. **Mayorkun the Mixer** - "Blends style with skill." (Double points in streaks)
4. **Paul the Plate** - "Keeps things balanced." (Has a magnet effect)
5. **Mide the Muncher** - "Bites back at bad food." (Slows less when hit)
6. **Tobi the Taster** - "Knows the good stuff." (Gets surprise power-ups)
7. **Stephan the Spatula** - "Flips the odds." (Shield lasts longer)
8. **Tomisin the Tomato** - "Cool under pressure." (Bonus during danger)
9. **Zubby the Zest** - "Adds life to the party." (Extra life spawns more often)
10. **Juyi the Juicer** - "Squeezes the best out of life." (Fruits give bonus points)

## Gameplay

### Good Fruits (Power-ups)
- **Apple** - +1 life
- **Banana** - Double points for 10 seconds
- **Orange** - Shield for 1 hit
- **Pineapple** - Magnet effect for 5 seconds

### Bad Foods
- **Burger/Pizza/Donut** - Lose 1 life each
- **Poison** - Lose 3 lives!

### Controls
- **Desktop**: Left/Right arrow keys or A/D keys
- **Mobile**: Swipe left/right
- **ESC**: Return to me

## Technical Details

- **Language**: Kotlin
- **Framework**: LibGDX 1.12.1
- **Target**: Desktop (with mobile input support)
- **Architecture**: Screen-based with entity-component pattern
- **Physics**: Simple collision detection (no Box2D)

## Game Loop

1. Items spawn at random intervals from the top of the screen
2. Player moves left/right to collect fruits and avoid bad foods
3. Collision detection triggers score/life/power-up effects
4. Difficulty increases over time with faster falling speeds
5. Game ends when lives reach 0

The game features a bright, cartoony art style with smooth animations and responsive controls for an engaging arcade experience!
