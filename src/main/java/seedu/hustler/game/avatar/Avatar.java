package seedu.hustler.game.avatar;

import seedu.hustler.game.shop.items.ShopItem;
import seedu.hustler.game.shop.items.armors.Armor;
import seedu.hustler.game.shop.items.weapons.Weapon;
import java.util.Optional;

/**
 * A class for the avatar in Hustler.
 */
public class Avatar implements Convertible {

    /**
     * The name of the avatar.
     */
    private String name;

    /**
     * Level of the avatar.
     */
    private Level level;

    /**
     * Stats of the avatar.
     */
    private Stats stats;

    /**
     * The equipped weapon of the avatar.
     */
    private Optional<Weapon> weapon;

    /**
     * The equipped armor of the avatar.
     */
    private Optional<Armor> armor;

    /**
     * Default initialization of the level and stat.
     */
    public Avatar() {
        this.name = "Avatar";
        this.level = new Level();
        this.stats = new Stats();
        this.weapon = Optional.empty();
        this.armor = Optional.empty();
    }

    /**
     * Initializing name, level and stat with specific
     * values.
     *
     * @param level object to initialize level with
     * @param stats object to initialize stats with
     */
    public Avatar(String name, Level level, Stats stats, Optional<Weapon> weapon, Optional<Armor> armor) {
        this.name = name;
        this.level = level;
        this.stats = stats;
        this.weapon = weapon;
        this.armor = armor;
    }

    /**
     * Sets the name for the avatar.
     * @param preferredName the new name to update to the avatar.
     * @return the avatar with the updated name.
     */
    public Avatar setName(String preferredName) {
        this.name = preferredName;
        return this;
    }

    /**
     * Gets the name for the avatar.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Increases avatar xp by 1. Avatar levels up and increases
     * its stats if the xp gained levels it up.
     *
     * @return the level of the avatar.
     */
    public Level gainXp() {
        this.level = level.increaseXp();
        if (this.level.canLevel()) {
            this.level = level.levelUp();
            this.stats = stats.upStats(this.level.getLevel());
        }
        return this.level;
    }

    public Avatar equip(ShopItem equipment) {
        if(equipment.getType().equals("Weapon")) {
            this.weapon = Optional.of((Weapon) equipment);
        } else if (equipment.getType().equals("Armor")) {
            this.armor = Optional.of((Armor) equipment);
        }
        return this;
    }

    public int getLevelInt() {
        return this.level.getLevel();
    }

    @Override
    public String toString() {
        String equipment = (weapon.isEmpty() && armor.isEmpty()) ? "" : ("\t--- Equipped ---"
            + (weapon.map(x -> "\n[ " + x.toString() + "]").orElse(""))
                + (armor.map(x -> "\n[ " + x.toString() + "]").orElse("")));
        return this.name + ", " + this.level.toString() + "\n"
            + this.stats.getStats(weapon, armor) + "\n" + equipment;
    }

    @Override
    public String toTxt() {
        return "Name " + this.name + "\n"
            + "Level " + this.level.toTxt() + "\n"
            + "Stats " + this.stats.toTxt() + "\n"
            + "Weapon " + this.weapon.map(Weapon::toString) + "\n"
            + "Armor " + this.armor.map(Armor::toString);
    }
}
