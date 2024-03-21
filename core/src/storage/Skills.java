package storage;

public abstract class Skills {
	private String name, description;
	private int dice;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String decription) {
		this.description = decription;
	}
	public int getDice() {
		return dice;
	}
	public void setDice(int dice) {
		this.dice = dice;
	}
}

class DoubleSwing extends Skills{
	public DoubleSwing() {
		setName("Double Swing");
		setDescription("Add a d8 to your Dexterity roll");
		setDice(8);
	}
}

class SkullBash extends Skills{
	public SkullBash() {
		setName("Skullbash");
		setDescription("Add a d4 to your Strength roll");
		setDice(4);
	}
}

class StandYourGround extends Skills{
	public StandYourGround() {
		setName("Stand Your Ground");
		setDescription("Add a d6 to your Vigor roll");
		setDice(6);
	}
}

class Warcry extends Skills{
	public Warcry() {
		setName("Warcry");
		setDescription("Throw the highest numbered dice twice");
	}
}

class Fireball extends Skills{
	public Fireball() {
		setName("Fireball");
		setDescription("Add 2d4 to your Wisdom roll");
	}
}

class Hailstorm extends Skills{
	public Hailstorm() {
		setName("Hailstorm");
		setDescription("Add a d6 to your Dexterity roll");
		setDice(6);
	}
}

class Blink extends Skills{
	public Blink() {
		setName("Blink");
		setDescription("Avoid damage taken");
	}
}

class Tweak extends Skills{
	public Tweak() {
		setName("Tweak");
		setDescription("Tweak your attributes, add a d12 to any roll");
		setDice(12);
	}
}

class ThrowKnife extends Skills{
	public ThrowKnife() {
		setName("Throw Knife");
		setDescription("Add a d4 to any roll");
		setDice(4);
	}
}

class Shadowstep extends Skills{
	public Shadowstep() {
		setName("Shadowstep");
		setDescription("Add a d8 to your Dexterity roll");
		setDice(8);
	}
}

class Stealth extends Skills{
	public Stealth() {
		setName("Stealth");
		setDescription("Add a d4 to your Strength roll & avoid damage taken");
		setDice(4);
	}
}

class Steal extends Skills{
	public Steal() {
		setName("Steal");
		setDescription("Lower the required objective number by 2");
	}
}