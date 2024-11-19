package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testPlayerInitialization() {
        // 測試玩家初始化
        Player player = new Player("Alice");

        // 檢查名稱是否正確
        assertEquals("Alice", player.getName());

        // 檢查起始金額是否為 1500
        assertEquals(1500, player.getMoney());

        // 檢查初始位置是否為 0
        assertEquals(0, player.getPosition());

        // 檢查初始是否不在監獄
        assertFalse(player.isInJail());

        // 檢查監獄回合數是否為 0
        assertEquals(0, player.getJailTurns());
    }

    @Test
    void testPlayerMoneyUpdate() {
        // 測試金錢增減
        Player player = new Player("Bob");

        // 增加金錢
        player.updateMoney(500);
        assertEquals(2000, player.getMoney());

        // 減少金錢
        player.updateMoney(-300);
        assertEquals(1700, player.getMoney());

        // 測試破產情況
        player.updateMoney(-2000);
        assertEquals(-300, player.getMoney());
        assertTrue(player.getMoney() < 0, "玩家應該破產");
    }

    @Test
    void testPlayerMovement() {
        // 測試玩家移動
        Player player = new Player("Charlie");

        // 移動 5 格
        player.move(5);
        assertEquals(5, player.getPosition(), "玩家應該在位置 5");

        // 移動超出 20 格（回到起點後的循環測試）
        player.move(16);
        assertEquals(1, player.getPosition(), "玩家應該回到位置 1");
    }

    @Test
    void testPlayerJailStatus() {
        // 測試監獄狀態
        Player player = new Player("Diana");

        // 測試進入監獄
        player.setInJail(true);
        assertTrue(player.isInJail(), "玩家應該在監獄中");
        assertEquals(3, player.getJailTurns(), "初始監獄回合數應為 3");

        // 減少監獄回合數
        player.decreaseJailTurn();
        assertEquals(2, player.getJailTurns(), "監獄回合數應減少到 2");

        // 測試出獄
        player.setInJail(false);
        assertFalse(player.isInJail(), "玩家應該已出獄");
        assertEquals(0, player.getJailTurns(), "監獄回合數應清零");
    }
}
