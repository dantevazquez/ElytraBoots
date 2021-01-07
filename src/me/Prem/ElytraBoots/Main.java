package me.Prem.ElytraBoots;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin implements Listener{
	
	@Override
	public void onEnable() {
		
		this.getServer().getPluginManager().registerEvents(this, this);
		
	}
	
	
	@Override
	public void onDisable() {
		
	}
	
	//Takes care of in game command part
	public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args){
		
		// /elytraboots
		if (label.equalsIgnoreCase("elytraboots")) {
			
			if (!(sender instanceof Player)) {
				sender.sendMessage("Not for you console");
				return true;
			}
			
			
			Player player= (Player)sender;
			
			if (player.getInventory().firstEmpty() == -1){//if inventory is full
				
				player.sendMessage("Can't give item. Inventory is full");
				return true;
			}
			
			if (player.isOp())
			{
			
				player.getInventory().addItem(getItem());
				return true;
			}
			
		}
		
		return false;
		
	}
	
	//Creating the item
	public ItemStack getItem() {
		
		ItemStack boots = new ItemStack(Material.CHAINMAIL_BOOTS); //creating the elytra boots
		
		//Speed Modifier
		AttributeModifier elytraSpeed = new AttributeModifier(UUID.randomUUID(), "player_speed", 0.1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
		
		ItemMeta meta = boots.getItemMeta();
		
		
		
		meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Elytra Boots"); //Item name
		
		//Item description
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.DARK_GRAY + "Happy Travels");
		meta.setLore(lore);
		
		
		meta.addEnchant(Enchantment.PROTECTION_FALL, 1 ,true); //Only to make item shiny
		meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, elytraSpeed); //IM STUCK HERE
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		
		boots.setItemMeta(meta); //confirm that item has meta
		
		
		return boots;
	}
	
	@EventHandler
	public void onJump(PlayerMoveEvent event) 
	{
		Player player = (Player) event.getPlayer();
		
			
		if (player.getInventory().getBoots() != null) //check if player has elytra boots on
		{
			
			if (player.getInventory().getBoots().getItemMeta().getDisplayName().contains("Elytra Boots"))
			{
				if (player.getInventory().getBoots().getItemMeta().getDisplayName().contains("Elytra Boots"))
				{
					
					if (player.getInventory().getBoots().getItemMeta().hasLore()) //check if item has a lore
					{
						if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType() == Material. ELYTRA)
						{
						
						}
						else if (player.getInventory().getItemInMainHand().getType().equals(Material.ROTTEN_FLESH))
						{
							
						}
						else if (event.getFrom().getY() < event.getTo().getY() //check if player is jumping from a block
								&& player.getLocation().subtract(0, 1, 1).getBlock().getType() != Material.AIR )
						{
							player.setVelocity(player.getLocation().getDirection().multiply(2).setY(2)); //jump boosts player
						}
						
					}
				}
					
				
			}
			
		}
		
	}
		
		
	
	
	@EventHandler
	public void onFall (EntityDamageEvent event) {
		
		if(event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			
			if (event.getCause() == DamageCause.FALL) {
				if (player.getInventory().getBoots() != null) //check if player has elytra boots on

					if (player.getInventory().getBoots().getItemMeta().getDisplayName().contains("Elytra Boots")) 
						
						if (player.getInventory().getBoots().getItemMeta().hasLore()) { //check if item has a lore
							event.setCancelled(true);
							
						}
						
			}
		}
	}
	
	
	
	
}
