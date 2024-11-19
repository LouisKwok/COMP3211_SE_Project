package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testPlayerInitialization() {
        // 測試初始化
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
        // 測試金錢更新
        Player player = new Player("Bob");

        // 測試加錢
        player.updateMoney(500);
        assertEquals(2000, player.getMoney());

        // 測試扣錢
        player.updateMoney(-300);
        assertEquals(1700, player.getMoney());

        // 測試破產情況
        player.updateMoney(-2000);
        assertEquals(-300, player.getMoney());
    }

    @Test
    void testPlayerMovement() {
        // 測試移動功能
        Player player = new Player("Charlie");

        // 測試移動 5 格
        player.move(5);
        assertEquals(5, player.getPosition());

        // 測試超過 20 格的情況
        player.move(16); // 總共 21 格，應該回到第 1 格
        assertEquals(1, player.getPosition());
    }

    @Test
    void testJailStatus() {
        // 測試監獄相關邏輯
        Player player = new Player("Diana");

        // 測試進監獄
        player.setInJail(true);
        assertTrue(player.isInJail());
        assertEquals(3, player.getJailTurns());

        // 測試減少監獄回合
        player.decreaseJailTurn();
        assertEquals(2, player.getJailTurns());

        // 測試出監獄
        player.setInJail(false);
        assertFalse(player.isInJail());
        assertEquals(0, player.getJailTurns());
    }
}
