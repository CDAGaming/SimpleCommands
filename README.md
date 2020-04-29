# TaylorCommands
Server commands for Fabric 20w15a+

Fork of [Markus23/mycommands](https://github.com/Markus23/mycommands)

## Changes

#### v0.2.0-20w16a.01

- Redesigned the config file so you should delete your current one to allow the new one to be created
- Added console logging, customizable in the config
- Added ability to make some commands enabled/disabled or OP Only

## Config

### Commands
Each command can be toggled as enabled, disabled or opOnly in the config. 
- `enabled` commands all players can use
- `opOnly` commands only players that are OP status can use
- `disabled` commands nobody can use

The config is laid out as follows:

```json
"commands": {
  "fly": {
    "enabled": true, 
    "opOnly": false
  }
}
```

Note: opOnly commands must have both `enabled` and `opOnly` set to `true`

### Logging

By default only `opOnly` commands are logged in the console, to log all commands, set the `player` value to `true`

```json
"log": {
  "opOnly": true,
  "player": true,
  "includeModName": false
}
```

`opOnly` will log the commands that have `opOnly: true` and `player` will log anything that has `opOnly: false`

The `includeModName` setting will prepend `[TaylorCommands]` before the logging output.

### Additional Settings

Some commands have additional settings (currently just `sethome`)

```json
"sethome": {
  "enabled": true,
  "opOnly": false,
  "max": 5
}
```

In this case `max` refers to the maximum number of homes a player can have, feel free to customize.

## Commands
### Home
#### Delete a home
`/delhome`

#### Warp to a set home
`/home`

#### Set a home
`/sethome`

### Teleport
#### Back to previous location
`/back`

#### Randomly teleport
`/rndtp`

#### Request to teleport
`/tpa`

#### Accept a teleport request
`/tpyes`
`/tpaccept`

#### Deny a teleport request
`/tpno`
`/tpdeny`

### Warp
#### Delete a warp
`/delwarp`

#### Warp to the spawn
`/spawn`

#### Set the spawn warp
`/setspawn`

#### Set a warp
`/setwarp`

#### Warp to a warp point
`/warp`


### Health
#### Restore full health and hunger
`/heal`

### Items
#### Repair item in hand
`/repair`

### Weather/Time
#### Set to daytime
`/day`

#### Set weather to raining
`/rain`

#### Set weather to sunny
`/sun`

### Player
#### Enable flying (like in creative)
`/fly`

#### Make player invulnerable
`/god`


