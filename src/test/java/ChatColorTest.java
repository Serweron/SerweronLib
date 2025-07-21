import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pl.serweron.serweronLib.colors.ChatColor;

public class ChatColorTest {

    @Test
    public void testChatColor() {
        // Test the ChatColor enum functionality
        assertEquals("§0", ChatColor.BLACK.toString());
        assertEquals(ChatColor.DARK_BLUE, ChatColor.fromName("DARK_BLUE"));
        assertEquals("Hello §aWorld", ChatColor.translate('&', "Hello &aWorld"));

        // Test all colors
        for (ChatColor color : ChatColor.values()) {
            assert color.toString().startsWith("§");
        }
    }
}
