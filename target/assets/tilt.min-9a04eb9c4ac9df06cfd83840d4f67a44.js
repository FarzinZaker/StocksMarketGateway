var TiltUtils = function (a, b, c) {
    function d(a, b, c, d) {
        for (var g = f[c], h = g.length; h--;)e ? a.attachEvent("on" + g[h], d) : a[b](g[h], d, !1)
    }

    if (!c.querySelectorAll)return null;
    var e = !document.addEventListener, f = function () {
        var c = {down: ["mousedown"], up: ["mouseup", "dragend"], resize: ["resize"]};
        return b.msPointerEnabled && (c.down.push("MSPointerDown"), c.up.push("MSPointerUp"), c.up.push("MSPointerCancel")), "ontouchstart"in a && (c.down.push("touchstart"), c.up.push("touchend"), c.up.push("touchcancel")), c
    }(), g = 1, h = ["perspective", "rotateX", "rotateY", "rotateZ", "translateX", "translateY", "translateZ"], i = h.length, j = {};
    return{hasTransforms: function () {
        return!e
    }, prefix: function () {
        for (var a, b = ["webkit", "Moz", "ms", "O"], d = 0, e = b.length, f = c.createElement("div").style; e > d; d++)if (a = b[d] + "Transform", a in f)return b[d];
        return null
    }(), getCSSRuleBySelector: function (a) {
        for (var b, c = document.styleSheets; b = c.pop();)for (var d = b.rules || b.cssRules, e = 0, f = d.length; f > e; e++)if (d[e].selectorText == a)return d[e].cssText ? d[e].cssText : d[e].style.cssText;
        return null
    }, initArray: function (a, b) {
        for (var c = [], d = b; d--;)c[d] = a;
        return c
    }, setTransforms: function (a, b) {
        a.__tiltId || (a.__tiltId = g++);
        var c = j[a.__tiltId] || null;
        c || (c = b);
        for (var d, e = 0, f = ""; i > e; e++)d = h[e], "undefined" != typeof b[d] && (c[d] = b[d]), c[d] && (f += d + "(" + c[d] + ") ");
        j[a.__tiltId] = c, this.setStyle(a, "-transform", f)
    }, getTransforms: function (a) {
        return a.__tiltId ? j[a.__tiltId] : null
    }, getStyle: function (a, b) {
        return"-" === b.charAt(0) && this.prefix ? (b = b.substring(1), a.style[this.prefix + b.charAt(0).toUpperCase() + b.slice(1)]) : a.style[b]
    }, addStyle: function (a, b, c) {
        var d = this.getStyle(a, b);
        this.setStyle(a, b, d + c)
    }, setStyle: function (a, b, c) {
        "-" === b.charAt(0) && this.prefix && (b = b.substring(1), a.style[this.prefix + b.charAt(0).toUpperCase() + b.slice(1)] = c), a.style[b] = c
    }, setStyles: function (a, b) {
        var c;
        for (c in b)b.hasOwnProperty(c) && this.setStyle(a, c, b[c])
    }, getOriginForValue: function (a, b) {
        return"opposite" === b ? Math.round(100 * (1 - a)) : 50
    }, getSpecs: function (b) {
        var c = b.getBoundingClientRect(), d = b && b.ownerDocument, e = d.documentElement;
        return{top: c.top + a.pageYOffset - e.clientTop, left: c.left + a.pageXOffset - e.clientLeft, width: c.width, height: c.height}
    }, listen: function (a, b, c) {
        d(a, "addEventListener", b, c)
    }, unlisten: function (a, b, c) {
        d(a, "removeEventListener", b, c)
    }}
}(window, navigator, document), TiltNode = function (a, b, c) {
    if (!c)return null;
    var d = function () {
        if (!c.hasTransforms())return 0;
        var b, d, e = document.createElement("div");
        return e.style.display = "none", document.documentElement.appendChild(e), e.className = "tilt", b = a.getComputedStyle(e, null), d = b[c.prefix + "Perspective"], e.parentNode.removeChild(e), parseInt(d, 10)
    }(), e = 2, f = -(d / 40), g = "opposite", h = 0, i = .125, j = .5, k = .375, l = function (a) {
        if (this._element = a, this._styles = {}, -1 !== this._element.className.indexOf("tilt-img")) {
            var b = this._element.querySelector("img");
            this._element.style.backgroundImage = "url(" + b.src + ")"
        }
        if (c.hasTransforms()) {
            var d = this;
            c.listen(this._element, "down", function (a) {
                d._onPush(a)
            })
        }
    };
    return l.prototype = {_hovers: function () {
        return-1 !== this._element.className.indexOf("hover")
    }, _getCSS: function (a) {
        return this._styles[a] || ""
    }, _onPush: function (b) {
        var l, m, n, o, p, q, r, s, t, u, v = this;
        t = a.getComputedStyle(this._element, null), this._styles.boxShadow = t.getPropertyValue("box-shadow"), c.listen(document, "up", function C() {
            c.unlisten(document, "up", C), v._onLift()
        }), l = c.getSpecs(this._element), m = (b.pageX - l.left) / l.width, n = (b.pageY - l.top) / l.height, r = c.getOriginForValue(m, g), s = c.getOriginForValue(n, g), o = d / l.height * e, p = d / l.width * e, q = 1 - (Math.abs(-.5 + m) + Math.abs(-.5 + n)), u = {perspective: d + "px", rotateX: (-.5 + n) * -o + "deg", rotateY: (-.5 + m) * p + "deg", translateZ: Math.round(q * f) + "px"};
        var w = {"-transformOrigin": r + "% " + s + "% 0"};
        if (this._hovers()) {
            var x, y, z, A;
            x = h + .333 * -(-.5 + m), y = i + .333 * -(-.5 + n), z = j - .3125 * q, A = k - .125 * q;
            var B = this._getCSS("box-shadow");
            w.boxShadow = (B ? B + "," : B) + x + "rem " + y + "rem " + z + "rem rgba(0,0,0," + A + ")"
        }
        c.setStyles(this._element.parentNode, {"-transform-style": "preserve-3d", "-transition": "none", "-transform": "none"}), c.setTransforms(this._element.parentNode, {translateZ: -f + "px"}), c.setStyles(this._element, w), c.setTransforms(this._element, u)
    }, _onLift: function () {
        if (this._hovers()) {
            var a = this._getCSS("box-shadow");
            c.setStyles(this._element, {boxShadow: (a ? a + "," : a) + h + "rem " + i + "rem " + j + "rem rgba(0,0,0," + k + ")"})
        }
        c.setTransforms(this._element, {perspective: d + "px", translateZ: null, rotateX: null, rotateY: null})
    }}, l
}(window, document, window.TiltUtils), TiltGrid = function (a, b) {
    if (!b)return null;
    var c = function (a) {
        this._element = a, this._maxTileSize = a.getAttribute("data-tile-size") || "m", this._element.setAttribute("data-tile-size", this._maxTileSize), this._maxTileActualWidth = this._getMaxTileWidthByTileSize(this._maxTileSize), this._grid = {slots: null, occupants: 0, wasted: 0, space: 0, width: 0, height: 0}, this._currentWidth = null, this._items = this._getItems(this._element), this._minHorizontalTileSlots = 4;
        var c = this, d = null;
        b.listen(window, "resize", function () {
            clearTimeout(d), c._isShrinking() ? (c._element.className += " tilt-static", setTimeout(function () {
                c._organize()
            }, 1)) : d = setTimeout(function () {
                c._organize()
            }, 50)
        }), this._organize();
        for (var e, f = 0, g = this._items.length; g > f; f++)e = this._items[f].node.children[0], b.setStyle(e, "-transitionDelay", .1 * f + "s");
        setTimeout(function () {
            c._element.setAttribute("data-ready", "true")
        }, 1)
    };
    return c.prototype = {_isShrinking: function () {
        return this._element.offsetWidth < this._currentWidth
    }, _getItems: function (a) {
        for (var b = a.children, c = [], d = 0, e = b.length; e > d; d++)c.push({node: b[d], offset: null, size: null});
        return c
    }, _getTileSize: function (a, b) {
        var c, d, e = a.getAttribute("data-tile-type") || "m", f = e.split(","), g = f.length, h = f[0], i = 1, j = !1;
        if (g > 1)for (; g > i; i++)if (e = f[i], c = parseInt(e, 10), isNaN(c))h = e.substring(1); else if (b >= c && (h = e.split(c + "")[1], c == b)) {
            j = !0;
            break
        }
        return d = this._getObjectByTileSize(h), d.explicit = j, d
    }, _setTileSize: function (a, b) {
        a.size = b, a.node.setAttribute("data-tile-size", this._getTileSizeByObject(b))
    }, _getHorizontalGridCellCount: function (a) {
        var b = 0, c = Math.round(a / 100);
        return"s" == this._maxTileSize && (b = 4 + c), "m" == this._maxTileSize && (b = 3 + c), "l" == this._maxTileSize && (b = 2 + c), Math.max(this._minHorizontalTileSlots, b)
    }, _getMaxTileHeightByHorizontalCellCount: function (a) {
        for (var b, c, d = 0, e = this._items.length, f = 1; e > d; d++)b = this._items[d].node, c = this._getTileSize(b, a), c.y > f && (f = c.y);
        return f
    }, _organize: function () {
        this._currentWidth = this._element.offsetWidth;
        var a, c = Math.max(this._minHorizontalTileSlots, Math.floor(this._currentWidth / this._maxTileActualWidth)), d = 0, e = Math.floor(this._currentWidth / c), f = 0, g = this._items.length, h = !1;
        if (this._grid.slots && c !== this._grid.slots[0].length) {
            for (h = !0; g > f; f++)a = this._items[f], a.offset = null, a.size = null;
            this._grid = {slots: null, occupants: 0, wasted: 0, space: 0, width: 0, height: 0, xSlots: 0, ySlots: 0}
        }
        for (this._grid.slots || (this._grid.slots = [b.initArray(0, c)]), f = 0; g > f; f++) {
            if (a = this._items[f], (!a.size || h && a.size && a.size.rescaled) && this._setTileSize(a, this._getTileSize(a.node, c)), !a.offset) {
                if (a.offset = this._findSlot(this._grid.slots, a.size), a.size.rescaled && this._setTileSize(a, a.size), !a.offset)continue;
                this._reserveSlot(this._grid.slots, a)
            }
            this._setItemPosition(a, e), a.offset.y + a.size.y > d && (d = a.offset.y + a.size.y)
        }
        this._element.style.height = d * e + "px", this._element.className = this._element.className.replace(" tilt-static", "")
    }, _setItemPosition: function (a, c) {
        b.hasTransforms() ? (b.setTransforms(a.node, {translateX: -Math.round(c * a.offset.x) + "px", translateY: Math.round(c * a.offset.y) + "px"}), b.setStyles(a.node, {width: c * a.size.x + "px", height: c * a.size.y + "px"})) : b.setStyles(a.node, {left: Math.round(c * a.offset.x) + "px", top: Math.round(c * a.offset.y) + "px", width: c * a.size.x + "px", height: c * a.size.y + "px"})
    }, _findSlot: function (a, b) {
        var c, d, e = a[0].length, f = b.x * b.y;
        for (f <= this._grid.wasted && (e = this._grid.width), d = 0; d < a.length; d++)for (c = 0; e > c; c++)if (this._isSlotAvailable(c, d, a, b))return{x: c, y: d};
        return!b.explicit && (b.x > 1 || b.y > 1) && this._grid.vacant > 0 && this._grid.vacant <= b.x * b.y ? (b.x > 1 ? b.x = Math.max(b.x / 2, 1) : b.y > 1 && (b.y = Math.max(b.y / 2, 1)), b.rescaled = !0, this._findSlot(a, b)) : (this._extendGrid(a), this._findSlot(a, b))
    }, _reserveSlot: function (a, b) {
        for (var c = b.offset.x, d = b.offset.y, e = b.size.x, f = b.size.y, g = d; d + f > g; g++)for (var h = c; c + e > h; h++)a[g][h] = 1;
        for (var i = 0, j = 0; j < a[0].length && 1 === a[0][j]; j++)i = j;
        this._grid.width = i + 1, this._grid.height = a.length, this._grid.ySlots = this._grid.height, this._grid.xSlots = this._grid.slots[0].length, this._grid.occupants += e * f, this._grid.space = this._grid.width * this._grid.height, this._grid.wasted = this._grid.space - this._grid.occupants, this._grid.vacant = this._grid.ySlots * this._grid.xSlots - this._grid.occupants
    }, _logState: function (a) {
        console.log("--------------------");
        for (var b = 0; b < a.length; b++)console.log(JSON.stringify(a[b]));
        console.log("width", this._grid.width), console.log("height", this._grid.height), console.log("space", this._grid.space), console.log("occupants", this._grid.occupants), console.log("wasted", this._grid.wasted), console.log("vacant", this._grid.vacant)
    }, _isSlotAvailable: function (a, b, c, d) {
        var e = d.x - 1, f = d.y - 1;
        if (this._isSlotTaken(c, a, b))return!1;
        if (0 === e && 0 === f)return!0;
        for (var g = b; b + f >= g; g++)for (var h = a; a + e >= h; h++) {
            if (!c[g])return!1;
            if (this._isSlotTaken(c, h, g))return!1
        }
        return!0
    }, _isSlotTaken: function (a, b, c) {
        return a[c] ? 0 !== a[c][b] : !1
    }, _extendGrid: function (a) {
        a.push(b.initArray(0, a[0].length))
    }, _getMaxTileWidthByTileSize: function (a) {
        return"m" == a ? 60 : "l" == a ? 75 : "s" == a ? 45 : 60
    }, _getObjectByTileSize: function (a) {
        if ("s" == a)return{x: 1, y: 1};
        if ("m" == a)return{x: 2, y: 2};
        if ("l" == a)return{x: 4, y: 4};
        if ("_s" == a)return{x: 2, y: 1};
        if ("_l" == a)return{x: 4, y: 2};
        if ("|s" == a)return{x: 1, y: 2};
        if ("|l" == a)return{x: 2, y: 4};
        throw new Error('unknown tile size: "' + a + '"')
    }, _getTileSizeByObject: function (a) {
        if (a.x == a.y) {
            if (1 == a.x)return"s";
            if (2 == a.x)return"m";
            if (4 == a.x)return"l"
        }
        if (a.x > a.y) {
            if (2 == a.x)return"_s";
            if (4 == a.x)return"_l"
        }
        if (a.x < a.y) {
            if (2 == a.y)return"|s";
            if (4 == a.y)return"|l"
        }
        return null
    }}, c
}(window, window.TiltUtils), TiltGroup = function (a, b) {
    return b ? function (a) {
        this._element = a;
        for (var c = a.querySelectorAll(".tilt-wrapper"), d = this, e = 0, f = c.length; f > e; e++)b.setStyle(c[e], "-transitionDelay", .1 * e + "s");
        setTimeout(function () {
            d._element.setAttribute("data-ready", "true")
        }, 1)
    } : null
}(window, window.TiltUtils);
!function (a, b, c, d, e) {
    function f() {
        var a, f;
        if (c)for (a = b.querySelectorAll(".tilt"), f = a.length; f--;)new c(a[f]);
        if (d)for (a = b.querySelectorAll(".tilt-grid"), f = a.length; f--;)new d(a[f]);
        if (d)for (a = b.querySelectorAll(".tilt-group"), f = a.length; f--;)new e(a[f])
    }

    b.querySelectorAll && ("complete" === b.readyState ? f() : b.addEventListener ? b.addEventListener("DOMContentLoaded", f) : (!function (a, b) {
        "undefined" != typeof module ? module.exports = b() : "function" == typeof define && "object" == typeof define.amd ? define(b) : this[a] = b()
    }("domready", function (a) {
        function b(a) {
            for (n = 1; a = d.shift();)a()
        }

        var c, d = [], e = !1, f = document, g = f.documentElement, h = g.doScroll, i = "DOMContentLoaded", j = "addEventListener", k = "onreadystatechange", l = "readyState", m = h ? /^loaded|^c/ : /^loaded|c/, n = m.test(f[l]);
        return f[j] && f[j](i, c = function () {
            f.removeEventListener(i, c, e), b()
        }, e), h && f.attachEvent(k, c = function () {
            /^c/.test(f[l]) && (f.detachEvent(k, c), b())
        }), a = h ? function (b) {
            self != top ? n ? b() : d.push(b) : function () {
                try {
                    g.doScroll("left")
                } catch (c) {
                    return setTimeout(function () {
                        a(b)
                    }, 50)
                }
                b()
            }()
        } : function (a) {
            n ? a() : d.push(a)
        }
    }), domready(f)))
}(window, document, window.TiltNode, window.TiltGrid, window.TiltGroup);
