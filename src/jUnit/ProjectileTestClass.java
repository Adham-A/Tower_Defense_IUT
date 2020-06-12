package jUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import model.enemy.*;
import model.projectile.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.TerrainLoaderException;
import model.Battlefield;

import mineralsRevenge.mRUtil;

public class ProjectileTestClass {

    private Battlefield battlefield;

    private Quartz quartz;
    private Emerald emerald;
    private Saphir saphir;
    private Diamond diamond;

    private Rocket rocket;
    private Potion potion;
    private Dynamite dynamite;

    @BeforeEach
    public void setUp() {
        try {
            this.battlefield = new Battlefield("battlefields/battlefield1.json");
        } catch (TerrainLoaderException e) {
            e.printStackTrace();
        }

        this.rocket = new Rocket(15, 15);
        this.potion = new Potion(15, 15);
        this.dynamite = new Dynamite(15, 15, battlefield);

        this.quartz = new Quartz(8, 20, battlefield);
        this.emerald = new Emerald(8, 22,  battlefield);
        this.saphir = new Saphir(7,22, battlefield);
        this.diamond = new Diamond(7, 23,  battlefield);

        this.battlefield.addEnemy(this.quartz);
        this.battlefield.addEnemy(this.emerald);
        this.battlefield.addEnemy(this.saphir);
        this.battlefield.addEnemy(this.diamond);
    }

	@Test
	void testMoveBack() {
		this.dynamite.shoot(this.quartz);
		// check the position of the quartz after getting pushed
		assertEquals(8, this.quartz.getX(), "Case: x");
		assertEquals(22, this.quartz.getY(), "Case: y");
	}

	@Test
	void testCollateralDamages() {
        // Pre-calculated values using the formula in collateralDamages() from the class Rocket.
        final int damageReduction_OneCellAway = 3;
        final int damageReduction_TwoCellAway = 6;

		this.rocket.shoot(this.emerald);

		assertEquals(mRUtil.emerald_hp - mRUtil.rocket_damage, this.emerald.getHp(), "Case: target of the rocket");
		assertEquals(mRUtil.quartz_hp - damageReduction_OneCellAway, this.quartz.getHp(), "Case: enemy who is one position ahead the target");
		assertEquals(mRUtil.saphir_hp - damageReduction_TwoCellAway, this.saphir.getHp(), "Case: enemy who is one position behind the target");
		assertEquals(mRUtil.diamond_hp, this.diamond.getHp(), "Case: enemy out of the radius");
    }

    @Test
    void testDebuff() {
        this.potion.shoot(this.diamond);
        assertFalse(this.diamond.hasArmor(), "Case: Potions Nullify Diamonds' Armor");

        // test if the projectile does damage to the diamond.
        this.potion.shoot(this.diamond);
        assertEquals(mRUtil.diamond_hp - mRUtil.potion_damage, this.diamond.getHp());
    }
}
