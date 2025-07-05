# 🔥 firefox-themes  
Switch Firefox themes from the command line with ease.

---

## ⚙️ Setup Instructions

### 1. Compile and Run the Program

```bash
javac firefoxThemes.java
java firefoxThemes
```

---

### 2. When Prompted, Enter the Following:

- **Create config:**

```bash
y
```

- **Theme path**  
  _(Recommended and default: `firefox-themes`)_

```bash
firefox-themes
```

- **Chrome path**  
  _(This is the path to your Firefox `chrome` folder — see below for how to find it.)_

```bash
/path/to/your/firefox/profile/chrome
```

---

### 3. How to Find the Chrome Folder

1. Open **Firefox**
2. In the address bar, type `about:support` and press **Enter**
3. Under **Profile Directory**, click **Open Directory**
4. Locate the `chrome` folder (create one if it doesn't exist)
5. Copy the full path to that folder
6. Paste it into the program when prompted and press **Enter**

---

### ⚠️ Required: Enable `userChrome.css` Support in Firefox

Before custom themes can work, Firefox needs to allow UI customization:

1. Open Firefox and go to:
   ```
   about:config
   ```
2. Accept the warning if prompted.
3. Search for:
   ```
   toolkit.legacyUserProfileCustomizations.stylesheets
   ```
4. Set it to `true` (double-click it to toggle).

5. Restart Firefox to apply the changes.

This tells Firefox to load your `userChrome.css` file at startup.

---

✅ That’s it — your config file will be created and you're ready to switch themes!
