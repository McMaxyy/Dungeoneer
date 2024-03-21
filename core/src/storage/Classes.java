package storage;

public abstract class Classes {
	private String name, trait, skill1, skill2, skill3;
	private int maxHP, strength, wisdom, dexterity, vigor;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTrait() {
		return trait;
	}
	public void setTrait(String trait) {
		this.trait = trait;
	}
	public String getSkill1() {
		return skill1;
	}
	public void setSkill1(String skill1) {
		this.skill1 = skill1;
	}
	public String getSkill2() {
		return skill2;
	}
	public void setSkill2(String skill2) {
		this.skill2 = skill2;
	}
	public String getSkill3() {
		return skill3;
	}
	public void setSkill3(String skill3) {
		this.skill3 = skill3;
	}
	public int getMaxHP() {
		return maxHP;
	}
	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}
	public int getStrength() {
		return strength;
	}
	public void setStrength(int strength) {
		this.strength = strength;
	}
	public int getWisdom() {
		return wisdom;
	}
	public void setWisdom(int intellect) {
		this.wisdom = intellect;
	}
	public int getDexterity() {
		return dexterity;
	}
	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}
	public int getVigor() {
		return vigor;
	}
	public void setVigor(int vigor) {
		this.vigor = vigor;
	}
}

class Barbarian extends Classes{
	public Barbarian() {
		setName("Barbarian");
		setTrait("Warcry");
		setSkill1("Double Swing");
		setSkill2("Skullbash");
		setSkill3("Stand Your Ground");
		setMaxHP(50);
		setStrength(12);
		setVigor(8);
		setWisdom(4);
		setDexterity(6);
	}
}

class Sorcerer extends Classes{
	public Sorcerer() {
		setName("Sorcerer");
		setTrait("Tweak");
		setSkill1("Fireball");
		setSkill2("Hailstorm");
		setSkill3("Blink");
		setMaxHP(35);
		setStrength(4);
		setVigor(6);
		setWisdom(12);
		setDexterity(8);
	}
}

class Thief extends Classes{
	public Thief() {
		setName("Thief");
		setTrait("Steal");
		setSkill1("Throw Knife");
		setSkill2("Shadowstep");
		setSkill3("Stealth");
		setMaxHP(40);
		setStrength(8);
		setVigor(4);
		setWisdom(6);
		setDexterity(12);
	}
}