//This class provides every constant used in the game
package mineralsRevenge;

public class mRUtil {
	
	/*
	 * Battlefield
	 */
	
	public final static int beginning_money = 100;
	public final static int beginning_hp = 20;
	
	public static int difficulty = 0;
	//-------------------------------------------------------------------
	
	/*
	 * Terrain
	 */
	
	public final static int startTileIdBeginning = 1;
	public final static int startTileIdEnd = 20;
	public final static int endTileIdBeginning = 21;
	public final static int endTileIdEnd = 40;
	public final static int roadTileIdBeginning = 41;
	public final static int roadTileIdEnd = 100;
	public final static int freeTileIdBeginning = 501;
	public final static int freeTileIdEnd = 600;
	
	//-------------------------------------------------------------------
	
	/*
	 * WaveManager
	 */
	
	public final static int maxTurn = 150;
	
	//SpawnTurn
	public final static int quartz_spawnTurn = 0;
	public final static int emerald_spawnTurn = 15;
	public final static int saphir_spawnTurn = 25;
	public final static int diamond_spawnTurn = 50;
	public final static int dwarfRenegade_spawnTurn = 75;
	
	//SpawnRates
	public final static int[] quartz_spawnrate = {2,2,1,1,1};
	public final static int[] emerald_spawnrate = {3,3,2,1,1};
	public final static int[] saphir_spawnrate = {5,5,5,3,2};
	public final static int[] diamond_spawnrate = {10,9,9,8,7};
	public final static int[] dwarfRenegade_spawnrate = {15,15,15,14,14};

	//-------------------------------------------------------------------
	
	/*
	 * Enemy
	 */
	
	//Quartz
	public final static int quartz_id = 201;
	public final static int quartz_hp = 30;
	public final static int quartz_money = 15 ;
	
	//Emerald
	public final static int emerald_id = 211;
	public final static int emerald_hp = 40;
	public final static int emerald_money = 25;
	public final static int emerald_baby1_id = 212;
	public final static int emerald_baby2_id = 213;
	
	//Saphir
	public final static int saphir_id = 221;
	public final static int saphir_hp = 50;
	public final static int saphir_money = 30 ;
	public final static int saphir_returnedDamageRatio = 8;
	
	//Diamond
	public final static int diamond_id = 231;
	public final static int diamond_hp = 50;
	public final static int diamond_money = 40;
	
	//DwarfRenegade
	public final static int dwarfRenegade_id = 241;
	public final static int dwarfRenegade_hp = 50;
	public final static int dwarfRenegade_money = 60;
	public final static int dwarfRenegade_range = 3;
	
	//-------------------------------------------------------------------
	
	/*
	 * Turret
	 */
	
	//DwarfMiner
	public final static int dwarfMiner_id = 101;
	public final static int dwarfMiner_hp = 25;
	public final static int dwarfMiner_range = 4;
	public final static int dwarfMiner_price = 15;

	//DwarfSoldier
	public final static int dwarfSoldier_id = 111;
	public final static int dwarfSoldier_hp = 40;
	public final static int dwarfSoldier_range = 4;
	public final static int dwarfSoldier_price = 50;
	
	//DwarfDemolitionist
	public final static int dwarfDemolitionist_id = 121;
	public final static int dwarfDemolitionist_hp = 30;
	public final static int dwarfDemolitionist_range = 3;
	public final static int dwarfDemolitionist_price = 30;
	
	//DwarfScientist
	public final static int dwarfScientist_id = 131;
	public final static int dwarfScientist_hp = 20;
	public final static int dwarfScientist_range = 5;
	public final static int dwarfScientist_price = 40;
	
	//Mine
	public final static int mine_id = 141;
	public final static int mine_income = 10;
	public final static int mine_hp = 150;
	public final static int mine_price = 450;
	
	//-------------------------------------------------------------------
	
	/*
	 * Projectile
	 */
	
	//Pickaxe
	public final static int pickaxe_id = 301;
	public final static int pickaxe_damage = 10;

	//Rocket
	public final static int rocket_id = 311;
	public final static int rocket_damage = 12;
	public final static int rocket_radius = 3;

	//Dynamite
	public final static int dynamite_id = 321;
	public final static int dynamite_damage = 6;
	
	//Potion
	public final static int potion_id = 331;
	public final static int potion_damage = 1;
	
	//RevengefulPickaxe
	public final static int revengefulPickaxe_id = 341;
	public final static int revengefulPickaxe_damage = 6;
	
	//-------------------------------------------------------------------
	
	//BattlefieldView
	public final static int tileSize = 32;

}