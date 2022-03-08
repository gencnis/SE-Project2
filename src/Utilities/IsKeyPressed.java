package Utilities;

/*
 *   Checks for Keys being pressed
 */
public class IsKeyPressed
{
    private static volatile boolean wPressed = false;

    public static boolean isWPressed()
    {
        synchronized (IsKeyPressed.class)
        {
            return wPressed;
        }
    }
}