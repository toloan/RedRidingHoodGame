package game.Object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class MainChar extends Personage {

	private int mana;
	private int maxMana;
	private int coin;// thÃƒÂªm
	private boolean key;
	private ArrayList<Item> item;
	private boolean active;
	private long stop;

	public void minusmana(int a) {
		if (mana > 0)
			mana -= a;
		else
			HP = 0;
	}

	public void plusMana(int a) {
		mana += a;
		if (mana > maxMana)
			mana = maxMana;
	}

	public long getStop() {
		return stop;
	}

	public void setStop(long l) {
		this.stop = l;
	}

	public MainChar(int x, int y) {
		super(x, y);
		key = false;
		this.huong = PHAI;
		HP = 4;
		maxHP = 4; // TODO: them gia tri maxHP
		speed = 5;
		item = new ArrayList<Item>();
		STEP = 50;
		setbeginItem();
		coin = 0;
		active = true;
		mana = 4;
		maxMana = 4;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	private void setbeginItem() {
		item.add(0, (Item) new Gun(x, y, 0, map));
		item.add(1, (Item) new Boom(x, y, 0, map));
		item.add(2, (Item) new Armor(x, y, 0));
		item.add(3, (Item) new Shoes(x, y, 0));
		for (int i = 0; i < item.size(); i++) {
			item.get(i).setItemType(-1);
		}

	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int c) {
		coin = c;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public ArrayList<Item> getItem() {
		return item;
	}

	public void move(char c) {
		switch (c) {
		case 'd':
			right();
			huong = PHAI;
			break;
		case 'a':
			left();
			huong = TRAI;
			break;
		case 's':
			down();
			huong = DUOI;
			break;
		case 'w':
			up();
			huong = TREN;
			break;
		case '1':
			if (((Weapon) item.get(0)).getTime() > 0) {
				
				item.get(0).setInUse(this);
			}

			break;
		case '2':
			if (((Weapon) item.get(1)).getTime() > 0) {
				
				item.get(1).setInUse(this);
			}
			break;
		case '3':
			if (((Armor) item.get(2)).getAbility() > 0) {
				
				item.get(2).setInUse(this);
			}
			break;
		case '4':
			System.out.println("4:");
			if (((Shoes) item.get(3)).getAbility() > 0) {
				
				item.get(3).setInUse(this);
			}
			break;
		default:
			break;
		}
		Item itemGot;
		itemGot = map.OneItemHere();
		if (itemGot != null) {
			gettingItem(itemGot);// TODO: goi ham lay do cua Main
			map.remove(itemGot);
		}
		
	}

	public boolean isHaveKey() {
		return key;
	}

	public void setKey() {
		this.key = true;
	}

	public void gettingItem(Item item1) {// sá»­a
		if (item1.getItemType() == item1.SUPPLY)
			item1.setInUse(this);
		else {
			int j = 0;
			if (item1.getItemType() == item1.WEAPON) {
				Weapon it = (Weapon) item1;
				int a = it.getWeaponType();
				for (int i = 0; i < item.size(); i++)// cÃ³ thá»ƒ sá»­a
				{
					if (item.get(i).getItemType() == item1.WEAPON) {
						Weapon it2 = (Weapon) item.get(i);
						if (a == it2.getWeaponType()) {
							it2.setTime(it.getTime() + it2.getTime());
							j = 1;
						}
					}
				}
				if (j == 0 && a == Weapon.GUN) {
					item.set(0, item1);
				} else if (j == 0 && a == Weapon.BOOM) {
					item.set(1, item1);
				}
			}
			// xá»­ lÃ½ support
			else if (item1.getItemType() == item1.SUPPORT) {
				Support it = (Support) item1;
				int a = it.getSupportType();
				for (int i = 0; i < item.size(); i++)// cÃ³ thá»ƒ sá»­a
				{
					if (item.get(i).getItemType() == item1.SUPPORT) {
						Support it2 = (Support) item.get(i);
						if (a == it2.getSupportType()) {							
							it2.setAbility(it.getAbility() + it2.getAbility());
						
							j = 1;
						}
					}
				}
				if (j == 0 && a == it.ARMOR)
					item.set(2, item1);
				if (j == 0 && a == it.SHOES)
					item.set(3, item1);
			}
		}
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public MainChar clone() {
		MainChar main2;
		main2 = new MainChar(0, 0);
		main2.maxHP = maxHP;
		main2.mana = mana;
		main2.coin = coin;
		main2.speed = speed;
		return main2;
	}


	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(Color.BLUE);

		for (int i = 0; i < this.mana; i++) {

			g.fillRect(x + i * 6, y - 20, 20, 4);
		}
		g.setColor(Color.gray);
		int remain = this.maxMana - this.mana;
		for (int i = 0; i < remain; i++) {

			g.fillRect(x + (getHP() + i) * 6, y - 20, 20, 4);
		}
		g.drawRect(x, y, 50, 50);
		BufferedImage img = null;

		switch (getHuong()) {
		case TRAI:
			try {
				img = ImageIO.read(new File("src/sprite/main_left.png"));// xem
																			// co
																			// chinh
																			// kich
																			// thuoc
																			// dc
																			// k
			} catch (IOException e) {
				e.printStackTrace();
			}
			g.drawImage(img, x, y, 50, 50, null);
			break;
		case TREN:
			try {
				img = ImageIO.read(new File("src/sprite/main_back.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			g.drawImage(img, x, y, 50, 50, null);
			break;
		case DUOI:
			try {
				img = ImageIO.read(new File("src/sprite/main_front.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			g.drawImage(img, x, y, 50, 50, null);
			break;
		case PHAI:
			try {
				img = ImageIO.read(new File("src/sprite/main_right.png"));// xem
																			// co
																			// chinh
																			// kich
																			// thuoc
																			// dc
																			// k
			} catch (IOException e) {
				e.printStackTrace();
			}
			g.drawImage(img, x, y, 50, 50, null);
			break;
		default:
			try {
				img = ImageIO.read(new File("src/sprite/main_left.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			g.drawImage(img, x, y, 50, 50, null);
			break;
		}
		// draw list item
		try {
			img = ImageIO.read(new File("src/sprite/gun.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		g.drawImage(img, 0, 900, 50, 50, null);
		g.drawString(Integer.toString(((Gun) item.get(0)).getTime()), 0, 950);
		try {
			img = ImageIO.read(new File("src/sprite/bomb.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		g.drawImage(img, 50, 900, 50, 50, null);
		g.drawString(Integer.toString(((Boom) item.get(1)).getTime()), 50, 950);
		try {
			img = ImageIO.read(new File("src/sprite/armor.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		g.drawImage(img, 100, 900, 50, 50, null);
		g.drawString(Integer.toString(((Armor) item.get(2)).getAbility()), 100, 950);
		try {
			img = ImageIO.read(new File("src/sprite/shoes.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		g.drawImage(img, 150, 900, 50, 50, null);
		g.drawString(Integer.toString(((Shoes) item.get(3)).getAbility()), 150, 950);
		try {
			img = ImageIO.read(new File("src/sprite/coin.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		g.drawImage(img, 200, 900, 50, 50, null);
		g.drawString(Integer.toString(getCoin()), 200, 950);

	}

	@Override
	public void update() {
		for (int i = 0; i < item.size(); i++) {
			item.get(i).setX(x);
			item.get(i).setY(y);
		}
		if (isActive() == false) {
			if ((System.currentTimeMillis() - stop) > 6000)
				
			{
				setActive(true);
				stop = 0;
			}
		}

	}
}
