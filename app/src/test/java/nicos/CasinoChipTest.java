package nicos;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import nicos.model.casino_chip.CasinoChip;
import nicos.model.casino_chip.ICasinoChip;

public class CasinoChipTest {
    @Test public void casinoChipTotal() {
        ICasinoChip casinoChip = new CasinoChip("100-20");
        assertTrue(casinoChip.totalValue()==2000);
    }
}
