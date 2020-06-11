package jUnit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.TerrainLoaderException;
import model.Battlefield;
import model.enemy.Diamond;
import model.enemy.Emerald;
import model.enemy.Quartz;
import model.enemy.Saphir;
import model.projectile.Pickaxe;


public class EnemyTestClass {
	
	private Diamond diamond;
	private Saphir saphir;
	private Emerald emerald;
	private Quartz quartz;
	private Battlefield battlefield;
	private Pickaxe damageDealer;
	
	@BeforeEach
	public void setUp() {
		try {
			this.battlefield = new Battlefield("battlefields/battlefield1.json");
		} catch (TerrainLoaderException e) {
			e.printStackTrace();
		}
		this.damageDealer = new Pickaxe(20, 0, 15, 15);
		this.diamond = new Diamond(80, 15, 15, battlefield);
		this.saphir = new Saphir(20,15,15,battlefield);
		this.emerald = new Emerald(15, 15, battlefield);
		this.quartz = new Quartz(15, 15, battlefield);
	}
	
	@Test
	void diamondResistDamage() {
		System.out.println(this.damageDealer);
		System.out.println(this.diamond);
		this.damageDealer.shoot(this.diamond);
		assertEquals(this.diamond.getHp(), 80);
	}

}
