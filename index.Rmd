---
title       : Modern Art UI - Android Devices
subtitle    : Graphic UI for Dedicated URL to MOMA
author      : B.A. McDougall
job         : NSCI Consulting
framework   : io2012        # {io2012, html5slides, shower, dzslides, ...}
highlighter : highlight.js  # {highlight.js, prettify, highlight}
hitheme     : tomorrow      # 
widgets     : []            # {mathjax, quiz, bootstrap}
mode        : selfcontained # {standalone, draft}
knit        : slidify::knit2slides
---

## Android Project - Modern Art UI

### Provides graphic interface for dedicated link to MOMA.org

### Uses "Overflow Menu" with Menu to "More Information"
* Provides user with options to open web browser that is linked to MOMA.org or cancel
* UI provides thumb slider that changes color of non-white / non-grey shapes
* Color transitions are reversible, but could be more smooth

## Current Status
* Link shows YouTube video of interface as of this writing

--- .class #id 
## Future Improvements
* Dynamically program shape sizes and shape placments based on screen size of host device
* Use ObjectAnimator.ofObject for more smooth transition between two color endpoints
* Interim improvement - use rgbEvaluator to smooth transition between color transitions

