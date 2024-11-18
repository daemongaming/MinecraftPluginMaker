#Minecraft Plugin Maker

How to easily make a Minecraft plugin using the Bukkit/Spigot API.



##Setup

* Eclipse IDE for Java
  - URL: https://www.eclipse.org/downloads/

* BuildTools
  - URL: https://www.spigotmc.org/wiki/buildtools/

1. Set up your Eclipse `workspace`.
2. Create a `New Project` in Eclipse, and name your package.
3. Create a `Main.java` file inside of the package to be your main class.
4. Create a `plugin.yml` file inside the root project directory.
5. Run BuildTools and get the `jar` files for CraftBukkit and Spigot.
6. Add the CraftBukkit and Spigot `jar` files to the `Build Path` of the project.
7. Set up your `Main.java` file with a `Main` class that `implements` `JavaPlugin`.
8. Add an `onEnable()` method to your `Main` class with an `@Override` tag.
9. Add values for `name`, `main`, and `version` to your `plugin.yml` file.



##APIs

An API is another library of classes and methods that can be referenced in one's own code. In fact, by creating public classes and methods, one's own project technically becomes its own API that can be referenced by other plugins!

Minecraft's API is proprietary, however much of it was decompiled and re-compiled as an API project that became known as CraftBukkit.

CraftBukkit has been succeeded by projects such as Spigot and Paper, as well as a number of other popular APIs which allow plugins & mods to 'hook' into a Minecraft server and modify the gameplay.

To use the Spigot API, first use BuildTools to create the `jar` files necessary (where `x.xx` is the version number):
* `craftbukkit-x.xx.jar`
* `spigot-api-x.xx-shaded.jar`

Simply add the `jar` files to your project's `Build Path`, and they can automatically be imported into your project as your code straight from your Eclipse IDE.



##Basic Java

###Variables & Data Types

Variables hold data according to their type until the program is shut down, or the variable is designated to be "garbage collected".

Variables may have modifiers like `public` or `private`, `static`, and `final` in front of the.
* `public` means accessible from any class
* `private` means accessible only from its parent class
* `static` means accessible even without an instance of its parent's class created first
* `final` means constant, i.e. does not change

####Example

`private int n = 126;` declares (creates) a variable called `n` of type `int` (an integer) that is equal to the numerical value `126` that can be only accessed inside its own class.

* `boolean` or `Boolean` is true/false.
  - i.e. `true`, `false`
* `int` or `Integer` is a whole number.
  - e.g. `0`, `1`, `-1`, `3`, `64`, `2147483647`
* `float` or `Float`, and `double` or `Double`, are rational numbers.
  - e.g. `3.14`, `0`, `-1`, `0.000001`
* `String` is text.
  - e.g. `'hello world'`, `"1337"`
* `T[]` is an array, or an ordered list, where `T` is the data type.
  - e.g. `{"karen", "john", "bob"}`, `{4, 2, 0, 6, 9, "blaze it"}`
* `List<T>` is an unordered list. `ArrayList<T>` is an ordered list.
  - e.g. `{0, 1, 1, 2, 3, 5}`, `{"i like turtles"}`, `{true, false, 'potato'}`
* `HashMap<T,T>` is a list of key-value pairs. `WeakHashMap<T,T>` is a less persistent version of HashMap.
  - e.g. `{1="a", 2="b", 3="c"}`, `{"found"=true, "do i care"=false}`
* `Object` is the basic kind of data type, which all other types can be cast as.
  - e.g. `0`, `3.14`, `"hello world"`, `true`, `{"karen", "john", "bob"}`



###Classes

Classes are objects that contain methods and variables that can be referenced by referencing the class.

Classes only need to start with the keyword `class` followed by their name and opening/closing curly brackets.

Classes may have modifiers such as `public` or `private`, `static`, and `final`.

####Example

`public class MyClass{ String myVar = "hello world!"; }` defines a class called `MyClass` which has the String variable `myVar` inside of it, that can be accessed later with `new MyClass().myVar`. 

Adding the `static` modifier will allow the variable to be called without first declaring `MyClass.myVar`

* Main class: the first class loaded, automatically; keeps everything connected
  - i.e. `public class Main extends JavaPlugin { @Override public void onEnable() { } }`
  - notes: do not create a new instance of your Main class, only use references to the main instance of it
* Listener classes: contains the EventHandler override to code custom response on events
  - i.e. `public class Listener implements Listener { }`
  - notes: this is usually where lag starts, since some events can be fired thousands of times per minute
* Custom classes: allow code to be abstracted into multiple "modules" or files instead of one big code file
  - i.e. `class Custom { }`
  - notes: remember to use your modifiers here to make your life easier later!



###Methods

Methods can be called from their parent class to perform the functions coded inside of them.

> [!NOTE]
> Methods can `return` an object as labeled in the declaration, or simply perform the function without returning anything at all by using the modifier `void`.

####Example

`public MyListener getListener() { return myListener; }` in the `Main` class, where one initialized a global variable `myListener` of class type `MyListener` in the `onEnable()` method using `MyListener myListener = new MyListener();"`, can be called in other classes to get the instance of that listener class with `MyListener myListener = mainClass.getListener();` where `mainClass` is the instance of one's `Main` class.

* `onEnable`/`onDisable` methods: called from the main class when the plugin starts/ends.
* `onCommand` method: called when a player enters a command via chat or console.



###Conditionals

Conditionals are ways to compare objects, returning a `true` or `false` value.

> [!NOTE]
> `false` is equal to `0`, and `true` is equal to `1`.

####Example

* `boolean condition = true;`
* `if (condition) { ... } else if (!condition) { ... } else { ... }`
* `switch (condition) { case true: ... case false: ... }`



###Loops

Loops can be used to continuously perform function until a condition is met.

####Example

* `for (int i=0; i<10; i++) { ... }` executes the code in the curly brackets 10 times by starting with `int i` at `0` and incrementing `i` with `i++` for every loop until `i<10` is no longer `true`.
* `for (Object obj : objects) { ... }` executes the code in the curly brackets for each `Object` in the `objects` list (or any `iterable` type object), inside the scope of which the `Object` `obj` is the object currently being iterated over.
* `while (condition) { ... }` executes the code in the curly brackets until `condition` is no longer true.

> [!TIP]
> Another more complex kind of loop is the do-while loop (`do { ... } while (condition)`).




##Spigot API

Now that you know the basics of Java, you can manipulate the types of objects, classes, and methods found in the Spigot API in order to modify your Minecraft server's gameplay.

Following here are some of the most common parts of the Spigot API that you will access for plugin development.


###Listener & EventHandler

A class which `implements Listener` can detect events occurring on the server and run custom code accordingly.

Listeners must be registered with the `PluginManager`, usually in the `onEnable()` method of your main class. The following code is a simple way to register a `Listener` object `listener` of type `MyListener`:

```
@Override
public void onEnable() {
	PluginManager pm = getServer().getPluginManager();
	MyListener listener = new MyListener();
	pm.registerEvents(listener, this);
}
```

Next, create the `MyListener` class. Here is an example of a basic `Listener` class that prints a message whenever a player joins the server:

```
public class MyListener implements Listener {
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String name = player.getName();
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			p.sendMessage(name + " has joined the server!");
		}
	}
}
``` 

The `@EventHandler` override tag in a `Listener` class, followed by a method declaration which takes in a parameter `EventType event` where `EventType` is the type of event object, runs its code every time the event occurs.

For instance, to run custom code that detects when a player breaks a block and cancels it, you would use something like the following in your `Listener` class:

```
@EventHandler
public void onBlockBreak(BlockBreakEvent event) {
	event.setCancelled(true);
}
```


###Player & Entity

The `Player` type is one of the most commonly accessed types in the Spigot API. It allows you to get all sorts of data about the player at the moment, as well as set that data to new values.

The `Player` type is a child of the `Entity` type, meaning that all `Player` type objects are also `Entity` type objects, but not vice versa.

Here is an example of giving a player a diamond whenever they kill a skeleton:

```
@EventHandler
public void onDeath(EntityDeathEvent event) {
	Entity killer = event.getDamageSource().getCausingEntity();
	Entity target = event.getEntity();
	boolean skelePK = killer instanceof Player && target instanceof Skeleton;
	if (skelePK) {
		Player player = (Player) killer;
		ItemStack item = new ItemStack(Material.DIAMOND, 1);
		player.getInventory().addItem(item);
	}
}
```

> [!WARNING]
> Notice that we don't assume the type of entities, rather we use conditional checks of whether they are an `instanceof` that type (`killer instanceof Player`) and *then* we can cast it to the type (`(Player) player`) to use that type's methods.


###ItemStack & Inventory

The `ItemStack` type is another commonly accessed type in the Spigot API. It contains all of the information about an item, including the complex `ItemMeta` child type containing everything from custom item name to the full text of a written book's pages.

For starting out, however, it is sufficient to know how to create the most basic form of an `ItemStack`, with essentially no meta attached to it, like so: 

`ItemStack item = new ItemStack(material, amount);`

where `material` is an `enum` value of type `Material` (such as `Material.IRON_PICKAXE` or Material.OAK_LOG) and `amount` is an `int` type value (such as `1` or `64`).

To access the `Inventory` of an `Entity` object, you simply use `getInventory()`.

> [!IMPORTANT]
> Entities have differenty types of inventories, so you should check for the entity's type first and then cast it to that type, and get the inventory from the resulting object.


###Block & Location

In order to manipulate the world, you'll need to work with `Location` and `Block` type objects.

A `Block` type object is very complex, much like an `ItemStack` object is, but again much of the work that is done with `Block` objects is fairly simple, like so:

`Block block = world.getBlock(location);`

where `world` is the world, of type `World` (which can be accessed with `Bukkit.getServer().getWorld("world");` where `"world"` is the name of the world) and `location` is of type `Location`.

A `Location` type can be retrieved from many other objects in the world, such as entities or blocks, but a new reference to a specific location can be made using the (x, y, z) coordinate system, like so:

`Location location = new Location(world, x, y, z)`

where `world` is the `World` object, and `x`, `y`, and `z` are their respective coordinate values.

> [!TIP]
> It is usually sufficient to use an `int` value for each `x`, `y`, and `z`, but `Location` accepts more specific decimal values as well.


###FileConfiguration

The `FileConfiguration` object is used to access data stored in a `File`, which is usually of the file type `yml` for Spigot plugins.

The data in a `FileConfiguration` object can be accessed by using the `get(key)` methods included to return the value associated with that key, and set with `set(key, value)` methods followed by the `saveConfig()` methods.

Using a `FileConfiguration` object can be a bit complex, but once you have the basics down it is a powerful tool for storing data from your program that would otherwise be lost when `onDisable()` is completed (aka, the server is shut down). This data can then be retrieved in `onEnable()` or wherever you want to call it.


###BukkitScheduler

A `BukkitScheduler` is a way to schedule a task (code) to be run in the future, at a determined time. These tasks can be scheduled to repeat at certain intervals, as well.

Again, using a `BukkitScheduler` can be complex at first, and care should be taken to note that any code run inside of the scheduler is done *asynchronously*, which means it will not have the access to the Spigot/Bukkit API that your plugin's code normally does.

> [!CAUTION]
> Running too many scheduled tasks can cause the server's resources to be used up far more than expected, so be careful with how often you call them.

Here is an example of a scheduler:

```
Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
	//Do something...
}, d, i);
```



##plugin.yml

Every plugin needs to have a `plugin.yml` file that contains useful basic information about the plugin, such as the name of the plugin, where to find the main class, and what commands can be used.

```
name: MyPlugin
main: com.yourname.myplugin.Main
version: 1
api-version: 1.21
commands:
     myFirstCommand:
        description: Hello world! My very first command!
        usage: /<command>
```

> [!NOTE]
> The `plugin.yml` file can have more values, however these are some of the most basic ones. The `name`, `main`, and `version` values are necessary, as is the `commands` section if commands are to be used.



##Exporting & Uploading

In order to test your plugin out, you have to export your project to a `jar` file with all of the appropriate files, and then upload the `jar` file to the `plugins` folder of your Minecraft server. Then, start/enable the server and test the plugin!

> [!CAUTION]
> Always stop your Minecraft server completely before uploading or editing files, or you risk causing unexpected errors.






















