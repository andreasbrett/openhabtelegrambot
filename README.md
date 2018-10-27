# openhabtelegrambot

This is an application to control OpenHab instance with Telegram BOT.
More details, instruction of use, and a demo on my blog:

https://regulargeek.blogspot.com/2018/06/control-openhab-using-telegram-messenger.html

# Modifications in this fork
 - added a DASH command to show a dashboard-like output of predefined items and their current states
 - changed message formatting for better readability
 - improved a bit upon security
 - the bot can now be limited to only talk to defined chat IDs
 - the bot can now be limited to control only defined openHAB items (regular expressions possible; check regex101.com)
 - item states can now be queried by providing item's name with wildcard (*) at the start or end
	- ```STATE myitem*``` will list all items that start with __myitem__
	- ```STATE *switch``` will list all items that end with __switch__

## application.properties

Here are some examples on how to use the new attributes __allowedChatIDs__, __allowedItems__, __readOnlyItems__ and __dashItems__. Please remember you HAVE to define them for __openhabtelegrambot__ to run.

```
// allow everyone to control/use the bot
allowedChatIDs=
allowedChatIDs=*

// allow only a single chat instance to control/use the bot
allowedChatIDs=1234

// allow several chat instances to control/use the bot
allowedChatIDs=1234,5678

// allow all items to be controlled through the bot
allowedItems=
allowedItems=*

// allow only a single item to be controlled through the bot
allowedItems=switchLightLivingRoom

// allow multiple items to be controlled through the bot
allowedItems=switchLightLivingRoom,switchLightBedRoom

// allow multiple items to be controlled through the bot (with regular expressions)
allowedItems=switchLight.*
allowedItems=switchLight.*,switchPowerOutlet.*

// all items are unrestricted, their states can be set
readOnlyItems=

// all items are read only, their states can't be set
readOnlyItems=*

// only a single item's state can be set
readOnlyItems=switchLightLivingRoom

// multiple items' state can be set
readOnlyItems=switchLightLivingRoom,switchLightBedRoom

// multiple items' state can be set (with regular expressions)
readOnlyItems=switchLight.*
readOnlyItems=switchLight.*,switchPowerOutlet.*


// no items are shown on the dashboard
dashItems=

// all items are shown on the dashboard (limited to allowedItems)
dashItems=*

// only a single item will be shown on the dashboard (limited to allowedItems)
dashItems=switchLightLivingRoom

// multiple items will be shown on the dashboard (limited to allowedItems)
dashItems=switchLightLivingRoom,switchLightBedRoom

// multiple items will be shown on the dashboard (with regular expressions; limited to allowedItems)
dashItems=switchLight.*
dashItems=switchLight.*,switchPowerOutlet.*
```
