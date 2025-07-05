#  firefox-themes  
Switch Firefox themes from the command line with ease.

https://github.com/user-attachments/assets/3454cd8a-b0a8-4ae0-9727-4525657c2613



---

## 🎨 How to Add Themes

`firefox-themes` comes with 4 built-in theme presets.

To add more themes:

1. Visit the [Firefox CSS Store](https://firefoxcss-store.github.io/)
2. Browse and install any theme you like
3. Copy the downloaded theme files into your selected theme folder  
   _(by default, this is `firefox-themes`)_
4. Restart Firefox — you're done!

> 💡 Themes usually include a `userChrome.css` file, sometimes along with other assets (images, variables, fonts, etc). Keep each theme in its own subfolder inside your theme directory for easy management.

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
2. Type `about:support` in the address bar and press **Enter**
3. Under **Profile Directory**, click **Open Directory**
4. Locate the `chrome` folder  
   _(create it if it doesn't exist)_
5. Copy the full path to that folder
6. Paste it into the program when prompted and press **Enter**

---

### ⚠️ Required: Enable `userChrome.css` Support in Firefox

Before custom themes can take effect, you must allow Firefox to load user styles.

1. Open Firefox and go to:
   ```
   about:config
   ```
2. Accept the warning if it appears
3. Search for:
   ```
   toolkit.legacyUserProfileCustomizations.stylesheets
   ```
4. Set it to `true` (double-click to toggle)
5. Restart Firefox

This tells Firefox to load your `userChrome.css` file at startup.

---

✅ That’s it — your config will be saved and you’re ready to switch themes!
