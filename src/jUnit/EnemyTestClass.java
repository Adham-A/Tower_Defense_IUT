package jUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import model.enemy.*;
import model.turret.*;
import model.projectile.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.TerrainLoaderException;
import model.Battlefield;

import mineralsRevenge.mRUtil;


public class EnemyTestClass {

	private Battlefield battlefield;

	private Quartz quartz;
	private Emerald emerald;
	private Saphir saphir;
	private Diamond diamond;

	private Pickaxe pickaxe;
	private Rocket rocket;
	private Potion potion;
	private Dynamite dynamite;

	private DwarfMiner miner;
	private DwarfSoldier soldier;
	private DwarfDemolitionist demolitionist;
	private DwarfScientist scientist;
	
	@BeforeEach
	public void setUp() {
		try {
			this.battlefield = new Battlefield("battlefields/battlefield1.json");
		} catch (TerrainLoaderException e) {
			e.printStackTrace();
		}

		this.pickaxe = new Pickaxe(15, 15);
		this.rocket = new Rocket(15, 15);
		this.potion = new Potion(15, 15);
		this.dynamite = new Dynamite(15, 15, battlefield);

		this.miner = new DwarfMiner(3, 5, battlefield);
		this.soldier = new DwarfSoldier(3, 6, battlefield);
		this.demolitionist = new DwarfDemolitionist(3, 7, battlefield);
		this.scientist = new DwarfScientist(4, 6, battlefield);

		this.quartz = new Quartz(8, 22, battlefield);
		this.emerald = new Emerald(7, 22,  battlefield);
		this.saphir = new Saphir(5,7, battlefield);
		this.diamond = new Diamond(7, 24,  battlefield);

		this.battlefield.addEnemy(this.quartz);
		this.battlefield.addEnemy(this.emerald);
		this.battlefield.addEnemy(this.saphir);
		this.battlefield.addEnemy(this.diamond);
	}

	@Test
	void testDiamondResistsToDamage() {
		this.pickaxe.shoot(this.diamond);
		assertEquals(mRUtil.diamond_hp, this.diamond.getHp(), "Case: pickaxe shoot the diamond");
		this.rocket.shoot(this.diamond);
		assertEquals(mRUtil.diamond_hp, this.diamond.getHp(), "Case: rocket shoot the diamond");
		this.dynamite.shoot(this.diamond);
		assertEquals(mRUtil.diamond_hp, this.diamond.getHp(), "Case: dynamite shoot the diamond");
		this.potion.shoot(this.diamond); // it resists to first attack before getting debuff.
		assertEquals(mRUtil.diamond_hp, this.diamond.getHp(), "Case: potion shoot the diamond");
	}

	@Test
	void testSplit() {
		for(Enemy e: this.battlefield.getEnemyList()) {
			if(e instanceof Emerald) {
				assertFalse(((Emerald) e).isAChild(), "Case: no emerald child before the split");
			}
		}

		this.emerald.setHp(0);
		this.emerald.action(); // it gives birth to 2 emeralds and removes the parent from the list.

		for(Enemy e: this.battlefield.getEnemyList()) {
			if(e instanceof Emerald) {
				assertTrue(((Emerald) e).isAChild(), "Case: presence of emerald child after the split");
			}
		}
	}
	// Check if turrets took back damages.
	@Test
	void testDealBackDamage() {
		this.miner.shoot();
		assertEquals(mRUtil.dwarfMiner_hp - (mRUtil.pickaxe_damage/mRUtil.saphir_returnedDamageRatio), this.miner.getHp(), "Case: Damage taken by the Miner from saphir");

		this.saphir.setHp(50); // reset hp to 50
		this.soldier.shoot();
		assertEquals(mRUtil.dwarfSoldier_hp - (mRUtil.rocket_damage/mRUtil.saphir_returnedDamageRatio),this.soldier.getHp(), "Case: Damage taken by the Soldier from saphir");

		this.saphir.setHp(50);
		this.demolitionist.shoot();
		assertEquals(mRUtil.dwarfDemolitionist_hp - (mRUtil.dynamite_damage/mRUtil.saphir_returnedDamageRatio),this.demolitionist.getHp(), "Case: Damage taken by the Demolitionist from saphir");

		this.saphir.setHp(50);
		this.scientist.shoot();
		assertEquals(mRUtil.dwarfScientist_hp - (mRUtil.potion_damage/mRUtil.saphir_returnedDamageRatio),this.scientist.getHp(), "Case: Damage taken by the Scientist from saphir");
	}
}
