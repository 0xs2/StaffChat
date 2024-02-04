# StaffChat

this plugin is just dead simple, straight to the point. does staffchat and nothing else.

<img src="https://i.imgur.com/FoOl2gQ.png" width="500px">

### settings.yml

```yml
settings:
    staffchat-prefix: '&c[StaffChat]&f'
    staffchat-toggle-alert: true
    staffchat-use-displayNamesStaffChat: false
    staffchat-use-displayNamesPublicChat: true
    staffchat-publicChatPrefix: '&f<%player%&f> '
config-version: 1
```

- Staff chat prefix, should be obvious
- Toggle alert, this sends the player a message letting them know staff chat is toggled upon joining.
- 'displayNamesStaffChat' - if you want any custom display name you have set or just their raw username displayed
- 'displayNamesPublicChat' - same thing.
- Public chat prefix, ideally you want to make this match the thing you have on your server.

### commands
- `/staffchat`, `/sc` - type in the staff chat
- `/staffchattoggle`, `/sct` - toggle the staff chat on/off
- `/staffchatcheck`, `/scc` - check if you or another user has staff chat toggled on or off
- `/staffchatmenu`, `/scm` - show the plugin information
- `/publicchat`, `/spc`, `/pc` - send a message to the public chat whilst staff chat is toggled

### permissions
- `staffchat.use` - use staffchat command
- `staffchat.public` - send a message to the public chat when active
- `staffchat.check` - Check if staffchat id toggled
- `staffchat.toggle` - toggle staffchat on/off
- `staffchat.menu` - see the staffchat menu
- `staffchat.see` - see staffchat
