-- Main entry point for Automator actions. 'input' contains a list of the added file paths.
on run {input, parameters}
	-- Exit immediately if no files were actually passed into the script
	if (count of input) is 0 then return input
	
	try
		-- The Finder block is required to access filesystem metadata like 'creation date' and 'name'
		tell application "Finder"
			-- Initialize sorting by assuming the first item in the list is the newest
			set latestFile to item 1 of input
			-- Coercing to 'alias' ensures AppleScript handles the file path correctly regardless of the environment
			set latestDate to creation date of (latestFile as alias)
			
			-- Edge case: If a batch of files is processed simultaneously, iterate to find the absolute newest one
			if (count of input) > 1 then
				repeat with anItem in input
					if (creation date of (anItem as alias)) > latestDate then
						set latestDate to creation date of (anItem as alias)
						set latestFile to anItem
					end if
				end repeat
			end if
			
			-- Extract the filename (including the extension) of the isolated newest file
			set fileName to name of (latestFile as alias)
		end tell
		
		-- Read the actual pixel data of the file and push it to the macOS clipboard as a raw image
		set the clipboard to (read (latestFile as alias) as TIFF picture)
		
	on error errMsg
		-- Notify the user that an error occurred, displaying the error message directly
		display notification errMsg with title "Screenshot Error"
	end try
	
	-- Automator expects the script to return the original input so it can be passed to the next action (if any exist)
	return input
end run