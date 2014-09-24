(function (e, t) {
    "use strict";
    function n(e, t) {
        for (var n, i = [], r = 0; e.length > r; ++r) {
            if (n = s[e[r]] || o(e[r]), !n)throw"module definition dependecy not found: " + e[r];
            i.push(n)
        }
        t.apply(null, i)
    }

    function i(e, i, r) {
        if ("string" != typeof e)throw"invalid module definition, module id must be defined and be a string";
        if (i === t)throw"invalid module definition, dependencies must be specified";
        if (r === t)throw"invalid module definition, definition function must be specified";
        n(i, function () {
            s[e] = r.apply(null, arguments)
        })
    }

    function r(e) {
        return!!s[e]
    }

    function o(t) {
        for (var n = e, i = t.split(/[.\/]/), r = 0; i.length > r; ++r) {
            if (!n[i[r]])return;
            n = n[i[r]]
        }
        return n
    }

    function a(n) {
        for (var i = 0; n.length > i; i++) {
            for (var r = e, o = n[i], a = o.split(/[.\/]/), l = 0; a.length - 1 > l; ++l)r[a[l]] === t && (r[a[l]] = {}), r = r[a[l]];
            r[a[a.length - 1]] = s[o]
        }
    }

    var s = {}, l = "moxman/util/Tools", u = "moxman/util/Class", c = "moxman/ui/Layout", d = "moxman/ui/AbsoluteLayout", f = "moxman/ui/BorderLayout", p = "moxman/ui/Selector", h = "moxman/ui/Collection", m = "moxman/ui/EventUtils", g = "moxman/ui/DomUtils", v = "moxman/ui/Control", x = "moxman/ui/Movable", y = "moxman/ui/Tooltip", b = "moxman/ui/Widget", w = "moxman/ui/Button", _ = "moxman/ui/Factory", E = "moxman/ui/Container", C = "moxman/ui/ButtonGroup", R = "moxman/ui/Carousel", T = "moxman/ui/Checkbox", A = "moxman/ui/CheckboxGroup", k = "moxman/ui/DragHelper", S = "moxman/ui/Scrollable", D = "moxman/ui/Panel", F = "moxman/ui/Resizable", I = "moxman/ui/FloatPanel", M = "moxman/ui/KeyboardNavigation", N = "moxman/ui/ColorButton", H = "moxman/ui/ComboBox", P = "moxman/ui/FormItem", O = "moxman/ui/Form", L = "moxman/ui/FieldSet", W = "moxman/ui/FitLayout", z = "moxman/ui/FlexLayout", B = "moxman/ui/FlowLayout", U = "moxman/ui/Grid", j = "moxman/ui/GridLayout", V = "moxman/ui/Iframe", q = "moxman/ui/Image", X = "moxman/gfx/Shader", G = "moxman/gfx/Texture", Y = "moxman/gfx/ImageEditor", $ = "moxman/util/Json", J = "moxman/util/Xhr", K = "moxman/util/JsonRpc", Z = "moxman/util/Path", Q = "moxman/Env", et = "moxie/core/utils/Basic", tt = "moxie/core/Exceptions", nt = "moxie/core/EventTarget", it = "moxie/core/utils/Encode", rt = "moxie/core/utils/Url", ot = "moxie/core/utils/Env", at = "moxie/core/utils/Dom", st = "moxie/runtime/Runtime", lt = "moxie/runtime/RuntimeClient", ut = "moxie/runtime/RuntimeTarget", ct = "moxie/file/Blob", dt = "moxie/xhr/FormData", ft = "moxie/core/I18n", pt = "moxie/core/utils/Mime", ht = "moxie/xhr/XMLHttpRequest", mt = "moxie/file/File", gt = "moxie/file/FileReaderSync", vt = "moxie/file/BlobBuilder", xt = "moxman/ui/ImageCanvas", yt = "moxman/ui/Label", bt = "moxman/ui/Toolbar", wt = "moxman/ui/MenuBar", _t = "moxman/ui/MenuButton", Et = "moxman/ui/ListBox", Ct = "moxman/ui/MenuItem", Rt = "moxman/ui/Menu", Tt = "moxman/ui/Window", At = "moxman/ui/MessageBox", kt = "moxman/ui/PanelButton", St = "moxman/ui/Path", Dt = "moxman/ui/Progress", Ft = "moxman/ui/Radio", It = "moxman/ui/RadioGroup", Mt = "moxman/ui/Slider", Nt = "moxman/ui/Spacer", Ht = "moxman/ui/SplitButton", Pt = "moxman/ui/StackLayout", Ot = "moxman/ui/TabPanel", Lt = "moxman/ui/TextBox", Wt = "moxman/ui/Throbber", zt = "moxman/ui/ThumbnailView", Bt = "moxman/ui/TreeItem", Ut = "moxman/ui/TreeView", jt = "moxman/ui/View", Vt = "moxman/ui/ViewPort", qt = "moxman/util/DateFormatter", Xt = "moxman/util/Fullscreen", Gt = "moxman/util/I18n", Yt = "moxman/util/ImageSize", $t = "moxman/util/Loader", Jt = "moxman/util/MinQuery", Kt = "moxman/util/Observable", Zt = "moxman/util/Shortcuts", Qt = "moxman/util/Uri", en = "moxman/util/When", tn = "moxman/vfs/FileCollection", nn = "moxman/vfs/File", rn = "moxman/vfs/FileSystem", on = "moxman/vfs/FileSystemManager", an = "moxman/views/LoginView", sn = "moxman/views/InstallView", ln = "moxman/views/BaseView", un = "moxman/views/CreateDirView", cn = "moxman/views/CreateDocView", dn = "moxman/views/EditView", fn = "moxman/Loader", pn = "moxman/views/FileListView", hn = "moxman/views/GalleryView", mn = "moxman/views/RenameView", gn = "moxie/file/FileInput", vn = "moxie/file/FileDrop", xn = "moxman/views/UploadView", yn = "moxman/views/ZipView", bn = "moxman/PluginManager", wn = "moxman/Manager", _n = "moxie/runtime/html5/Runtime", En = "moxie/runtime/html5/file/Blob", Cn = "moxie/core/utils/Events", Rn = "moxie/runtime/html5/file/FileDrop", Tn = "moxie/runtime/html5/file/FileInput", An = "moxie/core/JSON", kn = "moxie/runtime/html5/xhr/XMLHttpRequest", Sn = "moxie/runtime/flash/Runtime", Dn = "moxie/runtime/flash/file/Blob", Fn = "moxie/runtime/flash/file/FileInput", In = "moxie/runtime/Transporter", Mn = "moxie/runtime/flash/xhr/XMLHttpRequest", Nn = "moxie/runtime/flash/file/FileReaderSync";
    i(l, [], function () {
        return{makeMap: function (e, t, n) {
            var i;
            for (e = e || [], t = t || ",", "string" == typeof e && (e = e.split(t)), n = n || {}, i = e.length; i--;)n[e[i]] = {};
            return n
        }, unique: function (e) {
            for (var t = [], n = e.length, i; n--;)i = e[n], i.__checked || (t.push(i), i.__checked = 1);
            for (n = t.length; n--;)delete t[n].__checked;
            return t
        }, extend: function (e) {
            var t = arguments, n, i, r;
            for (i = 1; t.length > i; i++) {
                n = t[i];
                for (r in n)e[r] = n[r]
            }
            return e
        }, toArray: function (e) {
            var t = [], n, i;
            for (n = 0, i = e.length; i > n; n++)t[n] = e[n];
            return t
        }, inArray: function (e, t) {
            var n;
            if (t.indexOf)return t.indexOf(e);
            for (n = t.length; n--;)if (t[n] === e)return n;
            return-1
        }, isArray: Array.isArray || function (e) {
            return"[object Array]" === Object.prototype.toString.call(e)
        }, each: function (e, t) {
            var n, i, r, o;
            if (e)if (n = e.length, n === o) {
                for (i in e)if (e.hasOwnProperty(i) && t(e[i], i) === !1)return!1
            } else for (r = 0; n > r; r++)if (t(e[r], r) === !1)return!1;
            return!0
        }}
    }), i(u, [l], function (e) {
        function t() {
        }

        var n, i;
        return t.extend = n = function (t) {
            function r() {
                var e, t, n, r;
                if (!i && (r = this, r.init && r.init.apply(r, arguments), t = r.Mixins))for (e = t.length; e--;)n = t[e], n.init && n.init.apply(r, arguments)
            }

            function o() {
                return this
            }

            function a(e, t) {
                return function () {
                    var n = this, i = n._super, r;
                    return n._super = l[e], r = t.apply(n, arguments), n._super = i, r
                }
            }

            var s = this, l = s.prototype, u, c, d;
            i = !0, u = new s, i = !1, t.Mixins && (e.each(t.Mixins, function (e) {
                e = e;
                for (var n in e)"init" !== n && (t[n] = e[n])
            }), l.Mixins && (t.Mixins = l.Mixins.concat(t.Mixins))), t.Methods && e.each(t.Methods.split(","), function (e) {
                t[e] = o
            }), t.Properties && e.each(t.Properties.split(","), function (e) {
                var n = "_" + e;
                t[e] = function (e) {
                    var t = this, i;
                    return e !== i ? (t[n] = e, t) : t[n]
                }
            }), t.Statics && e.each(t.Statics, function (e, t) {
                r[t] = e
            }), t.Defaults && l.Defaults && (t.Defaults = e.extend({}, l.Defaults, t.Defaults));
            for (c in t)d = t[c], u[c] = "function" == typeof d && l[c] ? a(c, d) : d;
            return r.prototype = u, r.constructor = r, r.extend = n, r
        }, t
    }), i(c, [u, l], function (e, t) {
        return e.extend({Defaults: {firstControlClass: "first", lastControlClass: "last"}, init: function (e) {
            this.settings = t.extend({}, this.Defaults, e)
        }, preRender: function (e) {
            e.addClass(this.settings.containerClass, "body")
        }, applyClasses: function (e) {
            var t = this, n = t.settings, i, r, o;
            i = e.items().filter(":visible"), r = n.firstControlClass, o = n.lastControlClass, i.each(function (e) {
                e.removeClass(r).removeClass(o), n.controlClass && e.addClass(n.controlClass)
            }), i.eq(0).addClass(r), i.eq(-1).addClass(o)
        }, renderHtml: function (e) {
            var t = this, n = t.settings, i, r = "";
            return i = e.items(), i.eq(0).addClass(n.firstControlClass), i.eq(-1).addClass(n.lastControlClass), i.each(function (e) {
                n.controlClass && e.addClass(n.controlClass), r += e.renderHtml()
            }), r
        }, recalc: function () {
        }, postRender: function () {
        }})
    }), i(d, [c], function (e) {
        return e.extend({Defaults: {containerClass: "abs-layout", controlClass: "abs-layout-item"}, recalc: function (e) {
            e.items().filter(":visible").each(function (e) {
                var t = e.settings;
                e.layoutRect({x: t.x, y: t.y, w: t.w, h: t.h}), e.recalc && e.recalc()
            })
        }, renderHtml: function (e) {
            return'<div id="' + e._id + '-absend" class="' + e.classPrefix + 'abs-end"></div>' + this._super(e)
        }})
    }), i(f, [d], function (e) {
        return e.extend({recalc: function (e) {
            function t(e, t, n, i, r) {
                var o = e._marginBox;
                e.layoutRect({x: t + o.left, y: n + o.top, w: i + o.left + o.right, h: r + o.top + o.bottom, autoResize: !1}), e.recalc && e.recalc()
            }

            var n, i, r, o, a, s, l, u, c, d, f, p, h, m, g, v, x, y, b, w, _;
            for (n = e.layoutRect(), m = e.paddingBox(), i = n.innerW, r = n.innerH, s = m.left, l = m.top, u = i - (m.right + m.left), c = r - (m.bottom + m.top), d = e.items().filter(":visible"), p = 0; d.length > p; p++)f = d[p], w = f.settings, _ = f.layoutRect(), h = w.region, o = _.w, a = _.h, o > 0 && 1 > o && (o = Math.ceil(i * o)), a > 0 && 1 > a && (a = Math.ceil(r * a)), g = w.minWidth, v = w.minHeight, x = w.maxWidth, y = w.maxHeight, o = g && g > o ? g : o, a = v && v > a ? v : a, o = x && o > x ? x : o, a = y && a > y ? y : a, "north" === h ? (t(f, s, l, u, a), l += a, c -= a) : "east" === h ? (t(f, i - o - m.right, l, o, c), u -= o) : "south" === h ? (t(f, s, r - a - m.bottom, u, a), c -= a) : "west" === h ? (t(f, s, l, o, c), s += o, u -= o) : b = f;
            if (!b)throw Error("You must define a center region for border layout.");
            t(b, s, l, u, c)
        }})
    }), i(p, [u, l], function (e, n) {
        function i(e) {
            for (var t = [], n = e.length, i; n--;)i = e[n], i.__checked || (t.push(i), i.__checked = 1);
            for (n = t.length; n--;)delete t[n].__checked;
            return t
        }

        var r = /^([\w\\*]+)?(?:#([\w\\]+))?(?:\.([\w\\\.]+))?(?:\[\@?([\w\\]+)([\^\$\*!~]?=)([\w\\]+)\])?(?:\:(.+))?/i, o = /((?:\((?:\([^()]+\)|[^()]+)+\)|\[(?:\[[^\[\]]*\]|['"][^'"]*['"]|[^\[\]'"]+)+\]|\\.|[^ >+~,(\[\\]+)+|[>+~])(\s*,\s*)?((?:.|\r|\n)*)/g, a = /^\s*|\s*$/g, s, l = e.extend({init: function (e) {
            function n(e) {
                return e ? (e = e.toLowerCase(), function (t) {
                    return"*" === e || t.type === e
                }) : t
            }

            function i(e) {
                return e ? function (t) {
                    return t._name === e
                } : t
            }

            function s(e) {
                return e ? (e = e.split("."), function (t) {
                    for (var n = e.length; n--;)if (!t.hasClass(e[n]))return!1;
                    return!0
                }) : t
            }

            function l(e, n, i) {
                return e ? function (t) {
                    var r = t[e] ? t[e]() : "";
                    return n ? "=" === n ? r === i : "*=" === n ? r.indexOf(i) >= 0 : "~=" === n ? (" " + r + " ").indexOf(" " + i + " ") >= 0 : "!=" === n ? r != i : "^=" === n ? 0 === r.indexOf(i) : "$=" === n ? r.substr(r.length - i.length) === i : !1 : !!i
                } : t
            }

            function u(e) {
                var n;
                return e ? (e = /(?:not\((.+)\))|(.+)/i.exec(e), e[1] ? (n = d(e[1], []), function (e) {
                    return!f(e, n)
                }) : (e = e[2], function (t, n, i) {
                    return"first" === e ? 0 === n : "last" === e ? n === i - 1 : "even" === e ? 0 === n % 2 : "odd" === e ? 1 === n % 2 : t[e] ? t[e]() : !1
                })) : t
            }

            function c(e, t, o) {
                function c(e) {
                    e && t.push(e)
                }

                var d;
                return d = r.exec(e.replace(a, "")), c(n(d[1])), c(i(d[2])), c(s(d[3])), c(l(d[4], d[5], d[6])), c(u(d[7])), t.psuedo = !!d[7], t.direct = o, t
            }

            function d(e, t) {
                var n = [], i, r, a;
                do if (o.exec(""), r = o.exec(e), r && (e = r[3], n.push(r[1]), r[2])) {
                    i = r[3];
                    break
                } while (r);
                for (i && d(i, t), e = [], a = 0; n.length > a; a++)">" != n[a] && e.push(c(n[a], [], ">" === n[a - 1]));
                return t.push(e), t
            }

            var f = this.match;
            this._selectors = d(e, [])
        }, match: function (e, t) {
            var i, r, o, a, s, l, u, c, d, f, p, h, m;
            for (t = t || this._selectors, i = 0, r = t.length; r > i; i++) {
                for (s = t[i], a = s.length, m = e, h = 0, o = a - 1; o >= 0; o--)for (c = s[o]; m;) {
                    for (c.psuedo && (p = m.parent().items(), d = n.inArray(m, p), f = p.length), l = 0, u = c.length; u > l; l++)if (!c[l](m, d, f)) {
                        l = u + 1;
                        break
                    }
                    if (l === u) {
                        h++;
                        break
                    }
                    if (o === a - 1)break;
                    m = m.parent()
                }
                if (h === a)return!0
            }
            return!1
        }, find: function (e) {
            function t(e, i, r) {
                var o, a, s, l, u, c = i[r];
                for (o = 0, a = e.length; a > o; o++) {
                    for (u = e[o], s = 0, l = c.length; l > s; s++)if (!c[s](u, o, a)) {
                        s = l + 1;
                        break
                    }
                    if (s === l)r == i.length - 1 ? n.push(u) : u.items && t(u.items(), i, r + 1); else if (c.direct)return;
                    u.items && t(u.items(), i, r)
                }
            }

            var n = [], r, o, a = this._selectors;
            if (e.items) {
                for (r = 0, o = a.length; o > r; r++)t(e.items(), a[r], 0);
                o > 1 && (n = i(n))
            }
            return s || (s = l.Collection), new s(n)
        }});
        return l
    }), i(h, [l, p, u], function (e, n, i) {
        var r, o, a = Array.prototype.push, s = Array.prototype.slice;
        return o = {length: 0, init: function (e) {
            e && this.add(e)
        }, add: function (t) {
            var n = this;
            return e.isArray(t) ? a.apply(n, t) : t instanceof r ? n.add(t.toArray()) : a.call(n, t), n
        }, set: function (e) {
            var t = this, n = t.length, i;
            for (t.length = 0, t.add(e), i = t.length; n > i; i++)delete t[i];
            return t
        }, filter: function (e) {
            var t = this, i, o, a = [], s, l;
            for ("string" == typeof e ? (e = new n(e), l = function (t) {
                return e.match(t)
            }) : l = e, i = 0, o = t.length; o > i; i++)s = t[i], l(s) && a.push(s);
            return new r(a)
        }, slice: function () {
            return new r(s.apply(this, arguments))
        }, eq: function (e) {
            return-1 === e ? this.slice(e) : this.slice(e, +e + 1)
        }, each: function (t) {
            return e.each(this, t), this
        }, toArray: function () {
            return e.toArray(this)
        }, indexOf: function (e) {
            for (var t = this, n = t.length; n-- && t[n] !== e;);
            return n
        }, reverse: function () {
            return new r(e.toArray(this).reverse())
        }, hasClass: function (e) {
            return this[0] ? this[0].hasClass(e) : !1
        }, prop: function (e, n) {
            var i = this, r, o;
            return n !== r ? (i.each(function (t) {
                t[e] && t[e](n)
            }), i) : (o = i[0], o && o[e] ? o[e]() : t)
        }, exec: function (t) {
            var n = this, i = e.toArray(arguments).slice(1);
            return n.each(function (e) {
                e[t] && e[t].apply(e, i)
            }), n
        }}, e.each("fire on off show hide addClass removeClass append prepend before after reflow".split(" "), function (t) {
            o[t] = function () {
                var n = e.toArray(arguments);
                return this.each(function (e) {
                    t in e && e[t].apply(e, n)
                }), this
            }
        }), e.each("text name width height disabled active selected checked visible parent value data".split(" "), function (e) {
            o[e] = function (t) {
                return this.prop(e, t)
            }
        }), r = i.extend(o), n.Collection = r, r
    }), i(m, [], function () {
        function e(e, t, n, i) {
            e.addEventListener ? e.addEventListener(t, n, i || !1) : e.attachEvent && e.attachEvent("on" + t, n)
        }

        function n(e, t, n, i) {
            e.removeEventListener ? e.removeEventListener(t, n, i || !1) : e.detachEvent && e.detachEvent("on" + t, n)
        }

        function i(e, t) {
            function n() {
                return!1
            }

            function i() {
                return!0
            }

            var r, o = t || {};
            for (r in e)"layerX" !== r && "layerY" !== r && (o[r] = e[r]);
            return o.target || (o.target = o.srcElement || document), o.preventDefault = function () {
                o.isDefaultPrevented = i, e && (e.preventDefault ? e.preventDefault() : e.returnValue = !1)
            }, o.stopPropagation = function () {
                o.isPropagationStopped = i, e && (e.stopPropagation ? e.stopPropagation() : e.cancelBubble = !0)
            }, o.stopImmediatePropagation = function () {
                o.isImmediatePropagationStopped = i, o.stopPropagation()
            }, o.isDefaultPrevented || (o.isDefaultPrevented = n, o.isPropagationStopped = n, o.isImmediatePropagationStopped = n), o
        }

        function r(i, r, o) {
            function a() {
                o.domLoaded || (o.domLoaded = !0, r(c))
            }

            function s() {
                "complete" === u.readyState && (n(u, "readystatechange", s), a())
            }

            function l() {
                try {
                    u.documentElement.doScroll("left")
                } catch (e) {
                    return setTimeout(l, 0), t
                }
                a()
            }

            var u = i.document, c = {type: "ready"};
            u.addEventListener ? e(i, "DOMContentLoaded", a) : (e(u, "readystatechange", s), u.documentElement.doScroll && i === i.top && l()), e(i, "load", a)
        }

        function o() {
            function t(e, t) {
                var n, i, r, o;
                if (n = s[t][e.type])for (i = 0, r = n.length; r > i; i++)if (o = n[i], o && o.func.call(o.scope, e) === !1 && e.preventDefault(), e.isImmediatePropagationStopped())return
            }

            var o = this, s = {}, l, u, c, d, f;
            u = a + (+new Date).toString(32), d = "onmouseenter"in document.documentElement, c = "onfocusin"in document.documentElement, f = {mouseenter: "mouseover", mouseleave: "mouseout"}, l = 1, o.domLoaded = !1, o.events = s, o.bind = function (n, a, p, h) {
                function m(e) {
                    t(i(e || E.event), g)
                }

                var g, v, x, y, b, w, _, E = window;
                if (n && 3 !== n.nodeType && 8 !== n.nodeType) {
                    for (n[u] ? g = n[u] : (g = l++, n[u] = g, s[g] = {}), h = h || n, a = a.split(" "), x = a.length; x--;)y = a[x], w = m, b = _ = !1, "DOMContentLoaded" === y && (y = "ready"), o.domLoaded && "ready" === y ? p.call(h, i({type: y})) : (d || (b = f[y], b && (w = function (e) {
                        var n, r;
                        if (n = e.currentTarget, r = e.relatedTarget, r && n.contains)r = n.contains(r); else for (; r && r !== n;)r = r.parentNode;
                        r || (e = i(e || E.event), e.type = "mouseout" === e.type ? "mouseleave" : "mouseenter", e.target = n, t(e, g))
                    })), c || "focusin" !== y && "focusout" !== y || (_ = !0, b = "focusin" === y ? "focus" : "blur", w = function (e) {
                        e = i(e || E.event), e.type = "focus" === e.type ? "focusin" : "focusout", t(e, g)
                    }), v = s[g][y], v ? v.push({func: p, scope: h}) : (s[g][y] = v = [
                        {func: p, scope: h}
                    ], v.fakeName = b, v.capture = _, v.nativeHandler = w, "ready" === y ? r(n, w, o) : e(n, b || y, w, _)));
                    return n = v = 0, p
                }
            }, o.unbind = function (e, t, i) {
                var r, a, l, c, d, f;
                if (!e || 3 === e.nodeType || 8 === e.nodeType)return o;
                if (r = e[u]) {
                    if (f = s[r], t) {
                        for (t = t.split(" "), l = t.length; l--;)if (d = t[l], a = f[d]) {
                            if (i)for (c = a.length; c--;)a[c].func === i && a.splice(c, 1);
                            i && 0 !== a.length || (delete f[d], n(e, a.fakeName || d, a.nativeHandler, a.capture))
                        }
                    } else {
                        for (d in f)a = f[d], n(e, a.fakeName || d, a.nativeHandler, a.capture);
                        f = {}
                    }
                    for (d in f)return o;
                    delete s[r];
                    try {
                        delete e[u]
                    } catch (p) {
                        e[u] = null
                    }
                }
                return o
            }, o.fire = function (e, n, r) {
                var a, s;
                if (!e || 3 === e.nodeType || 8 === e.nodeType)return o;
                s = i(null, r), s.type = n;
                do a = e[u], a && t(s, a), e = e.parentNode || e.ownerDocument || e.defaultView || e.parentWindow; while (e && !s.isPropagationStopped());
                return o
            }, o.clean = function (e) {
                var t, n, i = o.unbind;
                if (!e || 3 === e.nodeType || 8 === e.nodeType)return o;
                if (e[u] && i(e), e.getElementsByTagName || (e = e.document), e && e.getElementsByTagName)for (i(e), n = e.getElementsByTagName("*"), t = n.length; t--;)e = n[t], e[u] && i(e);
                return o
            }, o.destory = function () {
                s = {}
            }
        }

        var a = "moxman-data-";
        return o
    }), i(g, [l, m], function (e, n) {
        var i = e.makeMap("fillOpacity fontWeight lineHeight opacity orphans widows zIndex zoom", " "), r = 0, o = new n, a = {id: function () {
            return"moxman-" + r++
        }, createFragment: function (e) {
            var t, n, i = document, r;
            for (r = i.createElement("div"), t = i.createDocumentFragment(), r.innerHTML = e; n = r.firstChild;)t.appendChild(n);
            return t
        }, getWindowSize: function () {
            var e, t, n = window, i = document, r = i.body, o = i.documentElement;
            return r.offsetWidth && (e = r.offsetWidth, t = r.offsetHeight), "CSS1Compat" == i.compatMode && o.offsetWidth && (e = o.offsetWidth, t = o.offsetHeight), n.innerWidth && n.innerHeight && (e = n.innerWidth, t = n.innerHeight), {w: e, h: t}
        }, getViewPort: function (e) {
            var t, n;
            return e = e ? e : this.win, t = e.document, n = this.boxModel ? t.documentElement : t.body, {x: e.pageXOffset || n.scrollLeft, y: e.pageYOffset || n.scrollTop, w: e.innerWidth || n.clientWidth, h: e.innerHeight || n.clientHeight}
        }, getDocumentSize: function () {
            var e = document, t, n, i, r, o, a, s, l, u = Math.max;
            return t = e.documentElement, n = e.body, i = u(t.scrollWidth, n.scrollWidth), r = u(t.clientWidth, n.clientWidth), o = u(t.offsetWidth, n.offsetWidth), a = u(t.scrollHeight, n.scrollHeight), s = u(t.clientHeight, n.clientHeight), l = u(t.offsetHeight, n.offsetHeight), {width: o > i ? r : i, height: l > a ? s : a}
        }, getSize: function (e) {
            var t, n, i;
            return e.getBoundingClientRect ? (i = e.getBoundingClientRect(), t = i.right - i.left, n = i.bottom - i.top) : (t = e.offsetWidth, n = e.offsetHeight), {width: t, height: n}
        }, getPos: function (e, t) {
            var n, i, r, o, a, s;
            if (n = i = 0, e)if (r = document, o = r.body, t = t || o, e.getBoundingClientRect)s = e.getBoundingClientRect(), e = r.documentElement, n = s.left + (e.scrollLeft || o.scrollLeft) - e.clientTop, i = s.top + (e.scrollTop || o.scrollTop) - e.clientLeft; else {
                for (a = e; a && a != t && a.nodeType;)n += a.offsetLeft || 0, i += a.offsetTop || 0, a = a.offsetParent;
                for (a = e.parentNode; a && a != t && a.nodeType;)n -= a.scrollLeft || 0, i -= a.scrollTop || 0, a = a.parentNode
            }
            return{x: n, y: i}
        }, get: function (e) {
            return document.getElementById(e)
        }, addClass: function (e, t) {
            t && !this.hasClass(e, t) && (e.className += e.className ? " " + t : t)
        }, removeClass: function (e, t) {
            this.hasClass(e, t) && (t = e.className.replace(RegExp("(^|\\s+)" + t + "(\\s+|$)", "g"), " "), " " === t ? e.removeAttribute("class") : e.className = t)
        }, hasClass: function (e, t) {
            return e && t && -1 !== (" " + e.className + " ").indexOf(" " + t + " ")
        }, toggleClass: function (e, n, i) {
            i = i === t ? !this.hasClass(e, n) : i, this.hasClass(e, n) !== i && (i ? this.addClass(e, n) : this.removeClass(e, n))
        }, attr: function (e, n, i) {
            return i === t ? e.getAttribute(n, i) : (e.setAttribute(n, i), t)
        }, css: function (e, n, r) {
            var o = e.style, a;
            if (n)if ("string" == typeof n) {
                n = n.replace(/-(\D)/g, function (e, t) {
                    return t.toUpperCase()
                }), "number" != typeof r || i[n] || (r += "px"), "opacity" === n && e.runtimeStyle && e.runtimeStyle.opacity === t && (o.filter = "" === r ? "" : "alpha(opacity=" + 100 * r + ")");
                try {
                    o[n] = r
                } catch (s) {
                }
            } else for (a in n)this.css(e, a, n[a])
        }, on: function (e, t, n, i) {
            return o.bind(e, t, n, i)
        }, off: function (e, t, n) {
            return o.unbind(e, t, n)
        }, fire: function (e, t, n) {
            return o.fire(e, t, n)
        }};
        return a
    }), i(v, [u, l, h, g], function (e, n, i, r) {
        var o = n.makeMap("focusin focusout scroll click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave wheel keydown keypress keyup contextmenu", " "), a = {}, s = "onmousewheel"in document, l = !1, u = e.extend({Statics: {controlIdLookup: {}, classPrefix: "moxman-"}, classPrefix: "moxman-", getContainerElm: function () {
            return document.body
        }, getParentCtrl: function (e) {
            for (var t; e && !(t = u.controlIdLookup[e.id]);)e = e.parentNode;
            return t
        }, init: function (e) {
            var i = this, o, a;
            if (i.settings = e = n.extend({}, i.Defaults, e), i._id = r.id(), i._text = i._name = "", i._width = i._height = 0, i._aria = {role: e.role}, o = e.classes)for (o = o.split(" "), o.map = {}, a = o.length; a--;)o.map[o[a]] = !0;
            i._classes = o || [], i.visible(!0), n.each("title text width height name classes visible disabled active value".split(" "), function (t) {
                var n = e[t], r;
                n !== r ? i[t](n) : i["_" + t] === r && (i["_" + t] = !1)
            }), i.on("click", function () {
                return i.disabled() ? !1 : t
            }), e.classes && n.each(e.classes.split(" "), function (e) {
                i.addClass(e)
            }), i.settings = e, i._borderBox = i.parseBox(e.border), i._paddingBox = i.parseBox(e.padding), i._marginBox = i.parseBox(e.margin), e.hidden && i.hide()
        }, Properties: "parent,title,text,width,height,disabled,active,name,value", Methods: "renderHtml,refresh", parseBox: function (e) {
            var t, n = 10;
            if (e)return"number" == typeof e ? (e = e || 0, {top: e, left: e, bottom: e, right: e}) : (e = e.split(" "), t = e.length, 1 === t ? e[1] = e[2] = e[3] = e[0] : 2 === t ? (e[2] = e[0], e[3] = e[1]) : 3 === t && (e[3] = e[1]), {top: parseInt(e[0], n) || 0, right: parseInt(e[1], n) || 0, bottom: parseInt(e[2], n) || 0, left: parseInt(e[3], n) || 0})
        }, borderBox: function () {
            return this._borderBox
        }, paddingBox: function () {
            return this._paddingBox
        }, marginBox: function () {
            return this._marginBox
        }, measureBox: function (e, t) {
            function n(t) {
                var n = document.defaultView;
                return n ? (t = t.replace(/[A-Z]/g, function (e) {
                    return"-" + e
                }), n.getComputedStyle(e, null).getPropertyValue(t)) : e.currentStyle[t]
            }

            function i(e) {
                var t = parseInt(n(e), 10);
                return isNaN(t) ? 0 : t
            }

            return{top: i(t + "TopWidth"), right: i(t + "RightWidth"), bottom: i(t + "BottomWidth"), left: i(t + "LeftWidth")}
        }, initLayoutRect: function () {
            var e = this, n = e.settings, i, r, o = e.getEl(), a, s, l, u, c, d, f;
            i = e._borderBox = e._borderBox || e.measureBox(o, "border"), e._paddingBox = e._paddingBox || e.measureBox(o, "padding"), e._marginBox = e._marginBox || e.measureBox(o, "margin"), d = n.minWidth, f = n.minHeight, l = d || o.offsetWidth, u = f || o.offsetHeight, a = n.width, s = n.height, c = n.autoResize, c = c !== t ? c : !a && !s, a = a || l, s = s || u;
            var p = i.left + i.right, h = i.top + i.bottom, m = n.maxWidth || 65535, g = n.maxHeight || 65535;
            return e._layoutRect = r = {x: n.x || 0, y: n.y || 0, w: a, h: s, deltaW: p, deltaH: h, contentW: a - p, contentH: s - h, innerW: a - p, innerH: s - h, startMinWidth: d || 0, startMinHeight: f || 0, minW: Math.min(l, m), minH: Math.min(u, g), maxW: m, maxH: g, autoResize: c, scrollW: 0}, e._lastLayoutRect = {}, r
        }, layoutRect: function (e) {
            var t = this, n = t._layoutRect, i, r, o, a, s, l;
            return n || (n = t.initLayoutRect()), e ? (o = n.deltaW, a = n.deltaH, e.x !== s && (n.x = e.x), e.y !== s && (n.y = e.y), e.minW !== s && (n.minW = e.minW), e.minH !== s && (n.minH = e.minH), r = e.w, r !== s && (r = n.minW > r ? n.minW : r, r = r > n.maxW ? n.maxW : r, n.w = r, n.innerW = r - o), r = e.h, r !== s && (r = n.minH > r ? n.minH : r, r = r > n.maxH ? n.maxH : r, n.h = r, n.innerH = r - a), r = e.innerW, r !== s && (r = n.minW - o > r ? n.minW - o : r, r = r > n.maxW - o ? n.maxW - o : r, n.innerW = r, n.w = r + o), r = e.innerH, r !== s && (r = n.minH - a > r ? n.minH - a : r, r = r > n.maxH - a ? n.maxH - a : r, n.innerH = r, n.h = r + a), e.contentW !== s && (n.contentW = e.contentW), e.contentH !== s && (n.contentH = e.contentH), i = t._lastLayoutRect, (i.x !== n.x || i.y !== n.y || i.w !== n.w || i.h !== n.h) && (l = u.repaintControls, l && l.map && !l.map[t._id] && (l.push(t), l.map[t._id] = !0), i.x = n.x, i.y = n.y, i.w = n.w, i.h = n.h), t) : n
        }, repaint: function () {
            function e(e, t, n) {
                return t > e && (e = t), e > n && (e = n), e
            }

            var t = this, n, i, r, o, a = 0, s = 0, l;
            n = t.getEl().style, r = t._layoutRect, l = t._lastRepaintRect || {}, o = t._borderBox, a = o.left + o.right, s = o.top + o.bottom, r.x !== l.x && (n.left = r.x + "px", l.x = r.x), r.y !== l.y && (n.top = r.y + "px", l.y = r.y), r.w !== l.w && (n.width = e(r.w - a, 0) + "px", l.w = r.w), r.h !== l.h && (n.height = e(r.h - s, 0) + "px", l.h = r.h), t._hasBody && r.innerW !== l.innerW && (i = t.getEl("body").style, i.width = e(r.innerW, 0) + "px", l.innerW = r.innerW), t._hasBody && r.innerH !== l.innerH && (i = i || t.getEl("body").style, i.height = e(r.innerH, 0) + "px", l.innerH = r.innerH), t._lastRepaintRect = l, t.fire("repaint", {}, !1)
        }, on: function (e, n) {
            function i(e) {
                var n, i;
                return function (o) {
                    return n || r.parents().each(function (r) {
                        var o = r.settings.callbacks;
                        return o && (n = o[e]) ? (i = r, !1) : t
                    }), n.call(i, o)
                }
            }

            var r = this, a, s, l, u;
            if (n)for ("string" == typeof n && (n = i(n)), l = e.toLowerCase().split(" "), u = l.length; u--;)e = l[u], a = r._bindings, a || (a = r._bindings = {}), s = a[e], s || (s = a[e] = []), s.push(n), o[e] && (r._nativeEvents ? r._nativeEvents[e] = !0 : r._nativeEvents = {name: !0}, r._rendered && r.bindPendingEvents());
            return r
        }, off: function (e, t) {
            var n = this, i, r = n._bindings, o, a, s, l;
            if (r)if (e)for (s = e.toLowerCase().split(" "), i = s.length; i--;) {
                if (e = s[i], o = r[e], !e) {
                    for (a in r)r[a].length = 0;
                    return n
                }
                if (o)if (t)for (l = o.length; l--;)o[l] === t && o.splice(l, 1); else o.length = 0
            } else n._bindings = [];
            return n
        }, fire: function (e, t, n) {
            function i() {
                return!1
            }

            function r() {
                return!0
            }

            var o = this, a, s, l, u;
            if (e = e.toLowerCase(), t = t || {}, t.type || (t.type = e), t.control || (t.control = o), t.preventDefault || (t.preventDefault = function () {
                t.isDefaultPrevented = r
            }, t.stopPropagation = function () {
                t.isPropagationStopped = r
            }, t.stopImmediatePropagation = function () {
                t.isImmediatePropagationStopped = r
            }, t.isDefaultPrevented = i, t.isPropagationStopped = i, t.isImmediatePropagationStopped = i), o._bindings && (l = o._bindings[e]))for (a = 0, s = l.length; s > a && (t.isImmediatePropagationStopped() || l[a].call(o, t) !== !1); a++);
            if (n !== !1)for (u = o.parent(); u && !t.isPropagationStopped();)u.fire(e, t, !1), u = u.parent();
            return t
        }, parents: function (e) {
            var t = this, n = new i;
            for (t = t.parent(); t; t = t.parent())n.add(t);
            return e && (n = n.filter(e)), n
        }, next: function () {
            var e = this.parent().items();
            return e[e.indexOf(this) + 1]
        }, prev: function () {
            var e = this.parent().items();
            return e[e.indexOf(this) - 1]
        }, findCommonAncestor: function (e, t) {
            for (var n; e;) {
                for (n = t; n && e != n;)n = n.parent();
                if (e == n)break;
                e = e.parent()
            }
            return e
        }, hasClass: function (e, t) {
            var n = this._classes[t || "control"];
            return e = this.classPrefix + e, n && !!n.map[e]
        }, addClass: function (e, t) {
            var n = this, i, r;
            return e = this.classPrefix + e, i = n._classes[t || "control"], i || (i = [], i.map = {}, n._classes[t || "control"] = i), i.map[e] || (i.map[e] = e, i.push(e), n._rendered && (r = n.getEl(t), r && (r.className = i.join(" ")))), n
        }, removeClass: function (e, t) {
            var n = this, i, r, o;
            if (e = this.classPrefix + e, i = n._classes[t || "control"], i && i.map[e])for (delete i.map[e], r = i.length; r--;)i[r] === e && i.splice(r, 1);
            return n._rendered && (o = n.getEl(t), o && (o.className = i.join(" "))), n
        }, toggleClass: function (e, t, n) {
            var i = this;
            return t ? i.addClass(e, n) : i.removeClass(e, n), i
        }, classes: function (e) {
            var t = this._classes[e || "control"];
            return t ? t.join(" ") : ""
        }, getEl: function (e, t) {
            var n, i = e ? this._id + "-" + e : this._id;
            return n = a[i] = (t === !0 ? null : a[i]) || r.get(i)
        }, visible: function (e) {
            var n = this, i;
            return e !== t ? (n._visible !== e && (n._rendered && (n.getEl().style.display = e ? "" : "none"), n._visible = e, i = n.parent(), i && (i._lastRect = null), n.fire(e ? "show" : "hide")), n) : n._visible
        }, show: function () {
            return this.visible(!0)
        }, hide: function () {
            return this.visible(!1)
        }, focus: function () {
            this.getEl().focus()
        }, blur: function () {
            this.getEl().blur()
        }, aria: function (e, n) {
            var i = this, r = i.getEl();
            return n === t ? i._aria[e] : (i._aria[e] = n, i._rendered && ("label" == e && r.setAttribute("aria-labeledby", i._id), r.setAttribute("role" == e ? e : "aria-" + e, n)), i)
        }, encode: function (e, t) {
            return t !== !1 && u.translate && (e = u.translate(e)), (e || "").replace(/[&<>"]/g, function (e) {
                return"&#" + e.charCodeAt(0) + ";"
            })
        }, before: function (e) {
            var t = this, n = t.parent();
            return n && n.insert(e, n.items().indexOf(t), !0), t
        }, after: function (e) {
            var t = this, n = t.parent();
            return n && n.insert(e, n.items().indexOf(t)), t
        }, remove: function () {
            var e = this, t = e.getEl(), n = e.parent(), i;
            if (e.items)for (var o = e.items().toArray(), a = o.length; a--;)o[a].remove();
            return n && n.items && (i = [], n.items().each(function (t) {
                t !== e && i.push(t)
            }), n.items().set(i), n._lastRect = null), e._eventsRoot && e._eventsRoot == e && r.off(t), delete u.controlIdLookup[e._id], t.parentNode && t.parentNode.removeChild(t), e
        }, renderBefore: function (e) {
            var t = this;
            return e.parentNode.insertBefore(r.createFragment(t.renderHtml()), e), t.postRender(), t
        }, renderTo: function (e) {
            var t = this;
            return e = e || t.getContainerElm(), e.appendChild(r.createFragment(t.renderHtml())), t.postRender(), t
        }, postRender: function () {
            var e = this, t = e.settings, n, i, o, a, s;
            for (a in t)0 === a.indexOf("on") && e.on(a.substr(2), t[a]);
            if (e._eventsRoot) {
                for (o = e.parent(); !s && o; o = o.parent())s = o._eventsRoot;
                if (s)for (a in s._nativeEvents)e._nativeEvents[a] = !0
            }
            e.bindPendingEvents(), t.style && (n = e.getEl(), n && (n.setAttribute("style", t.style), n.style.cssText = t.style)), e._visible || r.css(e.getEl(), "display", "none"), e.settings.border && (i = e.borderBox(), r.css(e.getEl(), {"border-top-width": i.top, "border-right-width": i.right, "border-bottom-width": i.bottom, "border-left-width": i.left})), u.controlIdLookup[e._id] = e;
            for (var l in e._aria)e.aria(l, e._aria[l]);
            e.fire("postrender", {}, !1)
        }, scrollIntoView: function (e) {
            function t(e, t) {
                var n, i, r = e;
                for (n = i = 0; r && r != t && r.nodeType;)n += r.offsetLeft || 0, i += r.offsetTop || 0, r = r.offsetParent;
                return{x: n, y: i}
            }

            var n = this.getEl(), i = n.parentNode, r, o, a, s, l, u, c = t(n, i);
            r = c.x, o = c.y, a = n.offsetWidth, s = n.offsetHeight, l = i.clientWidth, u = i.clientHeight, "end" == e ? (r -= l - a, o -= u - s) : "center" == e && (r -= l / 2 - a / 2, o -= u / 2 - s / 2), i.scrollLeft = r, i.scrollTop = o
        }, bindPendingEvents: function () {
            function e(e) {
                var t = o.getParentCtrl(e.target);
                t && t.fire(e.type, e)
            }

            function t() {
                var e = d._lastHoverCtrl;
                e && (e.fire("mouseleave", {target: e.getEl()}), e.parents().each(function (e) {
                    e.fire("mouseleave", {target: e.getEl()})
                }), d._lastHoverCtrl = null)
            }

            function n(e) {
                var t = o.getParentCtrl(e.target), n = d._lastHoverCtrl, i = 0, r, a, s;
                if (t !== n) {
                    if (d._lastHoverCtrl = t, a = t.parents().toArray().reverse(), a.push(t), n) {
                        for (s = n.parents().toArray().reverse(), s.push(n), i = 0; s.length > i && a[i] === s[i]; i++);
                        for (r = s.length - 1; r >= i; r--)n = s[r], n.fire("mouseleave", {target: n.getEl()})
                    }
                    for (r = i; a.length > r; r++)t = a[r], t.fire("mouseenter", {target: t.getEl()})
                }
            }

            function i(e) {
                e.preventDefault(), "mousewheel" == e.type ? (e.deltaY = -1 / 40 * e.wheelDelta, e.wheelDeltaX && (e.deltaX = -1 / 40 * e.wheelDeltaX)) : (e.deltaX = 0, e.deltaY = e.detail), e = o.fire("wheel", e)
            }

            var o = this, a, u, c, d, f, p;
            if (o._rendered = !0, f = o._nativeEvents) {
                for (c = o.parents().toArray(), c.unshift(o), a = 0, u = c.length; !d && u > a; a++)d = c[a]._eventsRoot;
                for (d || (d = c[c.length - 1] || o), o._eventsRoot = d, u = a, a = 0; u > a; a++)c[a]._eventsRoot = d;
                for (p in f) {
                    if (!f)return!1;
                    "wheel" !== p || l ? ("mouseenter" === p || "mouseleave" === p ? d._hasMouseEnter || (r.on(d.getEl(), "mouseleave", t), r.on(d.getEl(), "mouseover", n), d._hasMouseEnter = 1) : d[p] || (r.on(d.getEl(), p, e), d[p] = !0), f[p] = !1) : s ? r.on(o.getEl(), "mousewheel", i) : r.on(o.getEl(), "DOMMouseScroll", i)
                }
            }
        }, reflow: function () {
            return this.repaint(), this
        }});
        return u
    }), i(x, [g], function (e) {
        return{moveRel: function (t, n) {
            var i = this, r, o, a, s, l, u, c, d;
            return o = e.getPos(t), a = o.x, s = o.y, r = i.getEl(), l = r.offsetWidth, u = r.offsetHeight, c = t.offsetWidth, d = t.offsetHeight, n = (n || "").split(""), "b" === n[0] && (s += d), "r" === n[1] && (a += c), "c" === n[0] && (s += Math.round(d / 2)), "c" === n[1] && (a += Math.round(c / 2)), "b" === n[3] && (s -= u), "r" === n[4] && (a -= l), "c" === n[3] && (s -= Math.round(u / 2)), "c" === n[4] && (a -= Math.round(l / 2)), i.moveTo(a, s), i
        }, moveBy: function (e, t) {
            var n = this, i = n.layoutRect();
            return n.moveTo(i.x + e, i.y + t), n
        }, moveTo: function (t, n) {
            function i(e, t, n) {
                return 0 > e ? 0 : e + n > t ? (e -= n, 0 > e ? 0 : e) : e
            }

            var r = this;
            if (r.settings.contrainToViewport) {
                var o = e.getViewPort(window), a = r.layoutRect();
                t = i(t, o.w + o.x, a.w), n = i(n, o.h + o.y, a.h)
            }
            return r._rendered ? r.layoutRect({x: t, y: n}).repaint() : (r.settings.x = t, r.settings.y = n), r
        }}
    }), i(y, [v, x], function (e, n) {
        return e.extend({Mixins: [n], Defaults: {classes: "widget tooltip tooltip-n"}, text: function (e) {
            var n = this;
            return e !== t ? (n._value = e, n._rendered && (n.getEl().lastChild.innerHTML = n.encode(e)), n) : n._value
        }, renderHtml: function () {
            var e = this, t = e.classPrefix;
            return'<div id="' + e._id + '" class="' + e.classes() + '" role="presentation">' + '<div class="' + t + 'tooltip-arrow"></div>' + '<div class="' + t + 'tooltip-inner">' + e.encode(e._text) + "</div>" + "</div>"
        }, repaint: function () {
            var e = this, t, n;
            t = e.getEl().style, n = e._layoutRect, t.left = n.x + "px", t.top = n.y + "px", t.zIndex = 131070
        }})
    }), i(b, [v, y], function (e, t) {
        var n;
        return e.extend({init: function (e) {
            var t = this;
            t._super(e), t.canFocus = !0, e.tooltip && t.on("mouseenter mouseleave", function (n) {
                n.control == t && "mouseenter" == n.type ? t.tooltip().moveTo(-65535).text(e.tooltip).show().moveRel(t.getEl(), "bc tc") : t.tooltip().moveTo(-65535).hide()
            }), t.aria("label", e.tooltip)
        }, tooltip: function () {
            var e = this;
            return n || (n = new t({type: "tooltip"}), n.renderTo(e.getContainerElm())), n
        }, active: function (e) {
            var t = this, n;
            return e !== n && (t.aria("pressed", e), t.toggleClass("active", e)), t._super(e)
        }, disabled: function (e) {
            var t = this, n;
            return e !== n && (t.aria("disabled", e), t.toggleClass("disabled", e)), t._super(e)
        }, postRender: function () {
            var e = this, t = e.settings;
            e._rendered = !0, e._super(), e.parent() || !t.width && !t.height || (e.initLayoutRect(), e.repaint()), t.autofocus && setTimeout(function () {
                e.focus()
            }, 0)
        }, remove: function () {
            this._super(), n && (n.remove(), n = null)
        }})
    }), i(w, [b], function (e) {
        return e.extend({Defaults: {classes: "widget btn", role: "button"}, init: function (e) {
            var t = this, n;
            t.on("click mousedown", function (e) {
                e.preventDefault()
            }), t._super(e), n = e.size, e.subtype && t.addClass(e.subtype), n && t.addClass("btn-" + n)
        }, repaint: function () {
            var e = this.getEl().firstChild.style;
            e.width = e.height = "100%", this._super()
        }, renderHtml: function () {
            var e = this, t = e._id, n = e.classPrefix, i = e.settings.icon ? n + "ico " + n + "i-" + e.settings.icon : "", r = e.settings.image ? " style=\"background-image: url('" + e.settings.image + "')\"" : "";
            return'<div id="' + t + '" class="' + e.classes() + '" tabindex="-1">' + '<button role="presentation" tabindex="-1">' + (i ? '<i class="' + i + '"' + r + "></i>" : "") + (e._text ? (i ? " " : "") + e.encode(e._text) : "") + "</button>" + "</div>"
        }})
    }), i(_, [], function () {
        var e = {}, t;
        return{add: function (t, n) {
            e[t.toLowerCase()] = n
        }, has: function (t) {
            return!!e[t.toLowerCase()]
        }, create: function (n, i) {
            var r, o, a;
            if (!t) {
                a = moxman.ui;
                for (o in a)e[o.toLowerCase()] = a[o];
                t = !0
            }
            if ("string" == typeof n ? (i = i || {}, i.type = n) : (i = n, n = i.type), n = n.toLowerCase(), r = e[n], !r)throw Error("Could not find control by type: " + n);
            return r = new r(i), r.type = n, r
        }}
    }), i(E, [v, h, p, _, l, g], function (e, n, i, r, o, a) {
        var s = {};
        return e.extend({layout: "", innerClass: "container-inner", init: function (e) {
            var t = this;
            t._super(e), e = t.settings, t._items = new n, t.addClass("container"), t.addClass("container-body", "body"), e.containerCls && t.addClass(e.containerCls), t._layout = r.create((e.layout || t.layout) + "layout"), t.settings.items && t.add(t.settings.items), t._hasBody = !0
        }, items: function () {
            return this._items
        }, find: function (e) {
            return e = s[e] = s[e] || new i(e), e.find(this)
        }, add: function (e) {
            var t = this;
            return t.items().add(t.create(e)).parent(t), t
        }, focus: function () {
            var e = this;
            return e.keyNav ? e.keyNav.focusFirst() : e._super(), e
        }, replace: function (e, t) {
            for (var n, i = this.items(), r = i.length; r--;)if (i[r] === e) {
                i[r] = t;
                break
            }
            r >= 0 && (n = t.getEl(), n && n.parentNode.removeChild(n), n = e.getEl(), n && n.parentNode.removeChild(n)), t.parent(this)
        }, create: function (t) {
            var n = this, i, a = [];
            return o.isArray(t) || (t = [t]), o.each(t, function (t) {
                null !== t && (t instanceof e || ("string" == typeof t && (t = {type: t}), i = o.extend({}, n.settings.defaults, t), t.type = i.type = i.type || t.type || n.settings.defaultType || (i.defaults ? i.defaults.type : null), t = r.create(i)), a.push(t))
            }), a
        }, renderNew: function () {
            var e = this;
            return e.items().each(function (t, n) {
                var i, r;
                t.parent(e), t._rendered || (i = e.getEl("body"), r = a.createFragment(t.renderHtml()), i.hasChildNodes() && i.childNodes.length - 1 >= n ? i.insertBefore(r, i.childNodes[n]) : i.appendChild(r), t.postRender())
            }), e._layout.applyClasses(e), e._lastRect = null, e
        }, append: function (e) {
            return this.add(e).renderNew()
        }, prepend: function (e) {
            var t = this;
            return t.items().set(t.create(e).concat(t.items().toArray())), t.renderNew()
        }, insert: function (e, t, n) {
            var i = this, r, o, a;
            return e = i.create(e), r = i.items(), n || (t += 1), t >= 0 && r.length > t && (o = r.slice(0, t).toArray(), a = r.slice(t).toArray(), r.set(o.concat(e, a))), i.renderNew()
        }, preRender: function () {
        }, renderHtml: function () {
            var e = this, t = e._layout;
            return e.preRender(), t.preRender(e), '<div id="' + e._id + '" class="' + e.classes() + '" role="' + this.settings.role + '">' + '<div id="' + e._id + '-body" class="' + e.classes("body") + '">' + (e.settings.html || "") + t.renderHtml(e) + "</div>" + "</div>"
        }, postRender: function () {
            var e = this, t;
            return e.items().exec("postRender"), e._super(), e._layout.postRender(e), e._rendered = !0, e.settings.style && a.css(e.getEl(), e.settings.style), e.settings.border && (t = e.borderBox(), a.css(e.getEl(), {"border-top-width": t.top, "border-right-width": t.right, "border-bottom-width": t.bottom, "border-left-width": t.left})), e
        }, initLayoutRect: function () {
            var e = this, t = e._super();
            return e._layout.recalc(e), t
        }, recalc: function () {
            var e = this, n = e._layoutRect, i = e._lastRect;
            return i && i.w == n.w && i.h == n.h ? t : (e._layout.recalc(e), n = e.layoutRect(), e._lastRect = {x: n.x, y: n.y, w: n.w, h: n.h}, !0)
        }, reflow: function () {
            var t, n;
            if (this.visible()) {
                for (e.repaintControls = [], e.repaintControls.map = {}, n = this.recalc(), t = e.repaintControls.length; t--;)e.repaintControls[t].repaint();
                "flow" !== this.settings.layout && "stack" !== this.settings.layout && this.repaint(), e.repaintControls = []
            }
            return this
        }})
    }), i(C, [E], function (e) {
        return e.extend({Defaults: {defaultType: "button", role: "toolbar"}, renderHtml: function () {
            var e = this, t = e._layout;
            return e.addClass("btn-group"), e.preRender(), t.preRender(e), '<div id="' + e._id + '" class="' + e.classes() + '">' + '<div id="' + e._id + '-body">' + (e.settings.html || "") + t.renderHtml(e) + "</div>" + "</div>"
        }})
    }), i(R, [b], function (e) {
        return e.extend({Defaults: {classes: "carousel", checked: !1}, init: function (e) {
            var t = this;
            t._super(e), t._data = e.data, t._index = 0, t._first = !0, t.on("click", function (e) {
                var n = e.target.className;
                -1 != n.indexOf("prev") && t.prev(), -1 != n.indexOf("next") && t.next()
            })
        }, data: function (e) {
            this._data = e
        }, index: function (e, n) {
            function i(e) {
                var t, i = r.getEl("view");
                i.firstChild && i.removeChild(i.firstChild), t = new Image, t.id = r._id + "-img", t.className = r.classPrefix + "object", t.onload = function () {
                    var e = 50, o = i.clientWidth - e, a = i.clientHeight - e, s = t.clientWidth, l = t.clientHeight, u = Math.min(o / s, a / l);
                    1 > u && (s *= u, l *= u, t.style.width = s + "px", t.style.height = l + "px"), t.style.left = e / 2 + (o / 2 - s / 2) + "px", t.style.top = e / 2 + (a / 2 - l / 2) + "px", t.style.visibility = "visible", r._first = !r._first, n && n()
                }, t.style.width = t.style.height = "", t.src = e, i.appendChild(t)
            }

            var r = this;
            return e !== t ? (e >= 0 && this._data.length - 1 >= e && (r.getEl().firstChild.style.display = e > 0 ? "block" : "none", r.getEl().childNodes[1].style.display = r._data.length - 1 > e ? "block" : "none", i(this._data[e].url), r.fire("change", {index: e, lastIndex: r._index}), r._index = e), r) : r._index
        }, prev: function () {
            this.index(this.index() - 1)
        }, next: function () {
            this.index(this.index() + 1)
        }, postRender: function () {
            var e = this;
            e._super(), e.index(e.settings.selectedIndex || 0)
        }, renderHtml: function () {
            var e = this, t = e._id, n = e.classPrefix;
            return'<div id="' + t + '" class="' + e.classes() + '">' + '<div class="' + n + "dir " + n + 'prev" unselectable="true">&lsaquo;</div>' + '<div class="' + n + "dir " + n + 'next" unselectable="true">&rsaquo;</div>' + '<div id="' + t + '-view" class="' + n + 'view"></div>' + "</div>"
        }})
    }), i(T, [b], function (e) {
        return e.extend({Defaults: {classes: "checkbox", role: "checkbox", checked: !1}, init: function (e) {
            var t = this;
            t._super(e), t.on("click mousedown", function (e) {
                e.preventDefault()
            }), t.on("click", function (e) {
                e.preventDefault(), t.disabled() || t.checked(!t.checked())
            }), t.checked(t.settings.checked)
        }, checked: function (e) {
            var n = this;
            return e !== t ? (e ? n.addClass("checked") : n.removeClass("checked"), n._checked = e, n.aria("checked", e), n) : n._checked
        }, value: function (e) {
            return this.checked(e)
        }, renderHtml: function () {
            var e = this, t = e._id, n = e.classPrefix;
            return'<div id="' + t + '" class="' + e.classes() + '" unselectable="on" aria-labeledby="' + t + '-al" tabindex="-1">' + '<i class="' + n + "ico " + n + 'i-checkbox"></i>' + '<span id="' + t + '-al" class="' + n + 'label">' + e.encode(e._text) + "</span>" + "</div>"
        }})
    }), i(A, [E], function (e) {
        return e.extend({})
    }), i(k, [g], function (e) {
        function n() {
            var e = document, t, n, i, r, o, a, s, l, u = Math.max;
            return t = e.documentElement, n = e.body, i = u(t.scrollWidth, n.scrollWidth), r = u(t.clientWidth, n.clientWidth), o = u(t.offsetWidth, n.offsetWidth), a = u(t.scrollHeight, n.scrollHeight), s = u(t.clientHeight, n.clientHeight), l = u(t.offsetHeight, n.offsetHeight), {width: o > i ? r : i, height: l > a ? s : a}
        }

        return function (i, r) {
            function o() {
                return s.getElementById(r.handle || i)
            }

            var a, s = document, l, u, c, d, f, p;
            r = r || {}, u = function (t) {
                var i = n(), u, h;
                t.preventDefault(), l = t.button, u = o(), f = t.screenX, p = t.screenY, h = window.getComputedStyle ? window.getComputedStyle(u, null).getPropertyValue("cursor") : u.runtimeStyle.cursor, a = s.createElement("div"), e.css(a, {position: "absolute", top: 0, left: 0, width: i.width, height: i.height, zIndex: 65535, opacity: 1e-4, background: "red", cursor: h}), s.body.appendChild(a), e.on(s, "mousemove", d), e.on(s, "mouseup", c), r.start(t)
            }, d = function (e) {
                return e.button !== l ? c(e) : (e.deltaX = e.screenX - f, e.deltaY = e.screenY - p, e.preventDefault(), r.drag(e), t)
            }, c = function (t) {
                e.off(s, "mousemove", d), e.off(s, "mouseup", c), a.parentNode.removeChild(a), r.stop && r.stop(t)
            }, this.destroy = function () {
                e.off(o())
            }, e.on(o(), "mousedown", u)
        }
    }), i(S, [g, k], function (e, n) {
        return{init: function () {
            var e = this;
            e.on("repaint", e.renderScroll)
        }, renderScroll: function () {
            function i() {
                function n(n, s, l, u, c, d) {
                    var f, p, h, m, g, v, x, y, b;
                    if (p = o.getEl("scroll" + n)) {
                        if (y = s.toLowerCase(), b = l.toLowerCase(), o.getEl("absend") && e.css(o.getEl("absend"), y, o.layoutRect()[u] - 1), !c)return e.css(p, "display", "none"), t;
                        e.css(p, "display", "block"), f = o.getEl("body"), h = o.getEl("scroll" + n + "t"), m = f["client" + l] - 2 * a, m -= i && r ? p["client" + d] : 0, g = f["scroll" + l], v = m / g, x = {}, x[y] = f["offset" + s] + a, x[b] = m, e.css(p, x), x = {}, x[y] = f["scroll" + s] * v, x[b] = m * v, e.css(h, x)
                    }
                }

                var i, r, s;
                s = o.getEl("body"), i = s.scrollWidth > s.clientWidth, r = s.scrollHeight > s.clientHeight, n("h", "Left", "Width", "contentW", i, "Height"), n("v", "Top", "Height", "contentH", r, "Width")
            }

            function r() {
                function t(t, i, r, s, l) {
                    var u, c = o._id + "-scroll" + t, d = o.classPrefix;
                    o.getEl().appendChild(e.createFragment('<div id="' + c + '" class="' + d + "scrollbar " + d + "scrollbar-" + t + '">' + '<div id="' + c + 't" class="' + d + 'scrollbar-thumb"></div>' + "</div>")), o.draghelper = new n(c + "t", {start: function () {
                        u = o.getEl("body")["scroll" + i], e.addClass(e.get(c), d + "active")
                    }, drag: function (e) {
                        var n, c, d, f, p = o.layoutRect();
                        c = p.contentW > p.innerW, d = p.contentH > p.innerH, f = o.getEl("body")["client" + r] - 2 * a, f -= c && d ? o.getEl("scroll" + t)["client" + l] : 0, n = f / o.getEl("body")["scroll" + r], o.getEl("body")["scroll" + i] = u + e["delta" + s] / n
                    }, stop: function () {
                        e.removeClass(e.get(c), d + "active")
                    }})
                }

                o.addClass("scroll"), t("v", "Top", "Height", "Y", "Width"), t("h", "Left", "Width", "X", "Height")
            }

            var o = this, a = 2;
            o.settings.autoScroll && (o._hasScroll || (o._hasScroll = !0, r(), o.on("wheel", function (e) {
                var t = o.getEl("body");
                t.scrollLeft += 10 * (e.deltaX || 0), t.scrollTop += 10 * e.deltaY, i()
            }), e.on(o.getEl("body"), "scroll", i)), i())
        }}
    }), i(D, [E, S], function (e, n) {
        var i = e.extend({Defaults: {layout: "fit", containerCls: "panel"}, Mixins: [n], fromJSON: function (e) {
            var t = this;
            for (var n in e)t.find("#" + n).value(e[n]);
            return t
        }, toJSON: function () {
            var e = this, n = {};
            return e.find("*").each(function (e) {
                var i = e.name(), r = e.value();
                i && r !== t && (n[i] = r)
            }), n
        }, renderHtml: function () {
            var e = this, n = e._layout, i = e.settings.html;
            return e.preRender(), n.preRender(e), i === t ? i = '<div id="' + e._id + '-body" class="' + e.classes("body") + '">' + n.renderHtml(e) + "</div>" : ("function" == typeof i && (i = i()), e._hasBody = !1), '<div id="' + e._id + '" class="' + e.classes() + '" hideFocus="1" tabIndex="-1">' + (e._preBodyHtml || "") + i + "</div>"
        }});
        return i
    }), i(F, [g], function (e) {
        return{resizeToContent: function () {
            this._layoutRect.autoResize = !0, this._lastRect = null, this.reflow()
        }, resizeTo: function (t, n) {
            if (1 >= t || 1 >= n) {
                var i = e.getWindowSize();
                t = 1 >= t ? t * i.w : t, n = 1 >= n ? n * i.h : n
            }
            return this._layoutRect.autoResize = !1, this.layoutRect({minW: t, minH: n, w: t, h: n}).reflow()
        }, resizeBy: function (e, t) {
            var n = this, i = n.layoutRect();
            return n.resizeTo(i.w + e, i.h + t)
        }}
    }), i(I, [D, x, F, g], function (e, t, n, i) {
        var r, o = [], a = [], s, l = e.extend({Mixins: [t, n], init: function (e) {
            function t() {
                var e, t = l.zIndex || 65535, n;
                if (a.length)for (e = 0; a.length > e; e++)a[e].modal && (t++, n = a[e]), a[e].getEl().style.zIndex = t, a[e].zIndex = t, t++;
                var r = document.getElementById(u.classPrefix + "modal-block");
                n ? i.css(r, "z-index", n.zIndex - 1) : r && (r.parentNode.removeChild(r), s = !1), l.currentZIndex = t
            }

            function n(e, t) {
                for (; e;) {
                    if (e == t)return!0;
                    e = e.parent()
                }
            }

            var u = this;
            u._super(e), u._eventsRoot = u, u.addClass("floatpanel"), e.autohide && (r || (r = function (e) {
                var t, i = u.getParentCtrl(e.target);
                for (t = o.length; t--;)(!i || !n(i, o[t]) && o[t].parent() !== i) && o[t].hide()
            }, i.on(document, "click", r)), o.push(u)), u.on("postrender show", function (e) {
                if (e.control == u) {
                    var n, r = u.classPrefix;
                    u.modal && !s && (n = i.createFragment('<div id="' + r + 'modal-block" class="' + r + "reset " + r + 'fade"></div>'), n = n.firstChild, u.getContainerElm().appendChild(n), setTimeout(function () {
                        i.addClass(n, r + "in"), i.addClass(u.getEl(), r + "in")
                    }, 0), s = !0), a.push(u), t()
                }
            }), u.on("close hide", function (e) {
                if (e.control == u) {
                    for (var n = a.length; n--;)a[n] === u && a.splice(n, 1);
                    t()
                }
            }), e.popover && (u._preBodyHtml = '<div class="' + u.classPrefix + 'arrow"></div>', u.addClass("popover").addClass("bottom").addClass("start"))
        }, show: function () {
            var e = this, t, n = e._super();
            for (t = o.length; t-- && o[t] !== e;);
            return e.settings.autohide && -1 === t && o.push(e), n
        }, hide: function () {
            var e;
            for (e = o.length; e--;)o[e] === this && o.splice(e, 1);
            return this._super()
        }, hideAll: function () {
            l.hideAll()
        }, close: function () {
            var e = this;
            return e.fire("close"), e.remove()
        }, remove: function () {
            var e = this;
            e._super()
        }});
        return l.hideAll = function () {
            for (var e = o.length; e--;)o[e].fire("cancel", {}, !1), o[e].hide(), o.splice(e, 1)
        }, l
    }), i(M, [g], function (e) {
        return function (n) {
            function i() {
                if (!m)if (m = [], f.find)f.find("*").each(function (e) {
                    e.canFocus && m.push(e.getEl())
                }); else for (var e = f.getEl().getElementsByTagName("*"), t = 0; e.length > t; t++)e[t].id && e[t] && m.push(e[t])
            }

            function r() {
                return document.getElementById(g)
            }

            function o(e) {
                return e = e || r(), e && e.getAttribute("role")
            }

            function a(e) {
                for (var t, n = e || r(); n = n.parentNode;)if (t = o(n))return t
            }

            function s(e) {
                var n = document.getElementById(g);
                return n ? n.getAttribute("aria-" + e) : t
            }

            function l() {
                var t = r();
                if (!t || "TEXTAREA" != t.nodeName && "text" != t.type)return n.onAction ? n.onAction(g) : e.fire(r(), "click", {keyboard: !0}), !0
            }

            function u() {
                var e;
                n.onCancel ? ((e = r()) && e.blur(), n.onCancel()) : n.root.fire("cancel")
            }

            function c(e) {
                var t = -1, r, o;
                for (i(), o = m.length; o--;)if (m[o].id === g) {
                    t = o;
                    break
                }
                t += e, 0 > t ? t = m.length - 1 : t >= m.length && (t = 0), r = m[t], r.focus(), g = r.id, n.actOnFocus && l()
            }

            function d() {
                var e, r;
                for (r = o(n.root.getEl()), i(), e = m.length; e--;)if ("toolbar" == r && m[e].id === g)return m[e].focus(), t;
                m[0].focus()
            }

            var f = n.root, p = n.enableUpDown !== !1, h = n.enableLeftRight !== !1, m = n.items, g;
            return f.on("keydown", function (e) {
                var t = 37, i = 39, r = 38, d = 40, f = 27, m = 14, g = 13, v = 32, x = 9, y;
                switch (e.keyCode) {
                    case t:
                        h && (n.leftAction ? n.leftAction() : c(-1), y = !0);
                        break;
                    case i:
                        h && ("menuitem" == o() && "menu" == a() ? s("haspopup") && l() : c(1), y = !0);
                        break;
                    case r:
                        p && (c(-1), y = !0);
                        break;
                    case d:
                        p && ("menuitem" == o() && "menubar" == a() ? l() : "button" == o() && s("haspopup") ? l() : c(1), y = !0);
                        break;
                    case x:
                        y = !0, e.shiftKey ? c(-1) : c(1);
                        break;
                    case f:
                        y = !0, u();
                        break;
                    case m:
                    case g:
                    case v:
                        y = l()
                }
                y && (e.stopPropagation(), e.preventDefault())
            }), f.on("focusin", function (e) {
                i(), g = e.target.id
            }), {moveFocus: c, focusFirst: d, cancel: u}
        }
    }), i(N, [w, I, M], function (e, t, n) {
        function i() {
            var e, t = [], n;
            for (n = ["000000", "Black", "993300", "Burnt orange", "333300", "Dark olive", "003300", "Dark green", "003366", "Dark azure", "000080", "Navy Blue", "333399", "Indigo", "333333", "Very dark gray", "800000", "Maroon", "FF6600", "Orange", "808000", "Olive", "008000", "Green", "008080", "Teal", "0000FF", "Blue", "666699", "Grayish blue", "808080", "Gray", "FF0000", "Red", "FF9900", "Amber", "99CC00", "Yellow green", "339966", "Sea green", "33CCCC", "Turquoise", "3366FF", "Royal blue", "800080", "Purple", "999999", "Medium gray", "FF00FF", "Magenta", "FFCC00", "Gold", "FFFF00", "Yellow", "00FF00", "Lime", "00FFFF", "Aqua", "00CCFF", "Sky blue", "993366", "Brown", "C0C0C0", "Silver", "FF99CC", "Pink", "FFCC99", "Peach", "FFFF99", "Light yellow", "CCFFCC", "Pale green", "CCFFFF", "Pale cyan", "99CCFF", "Light sky blue", "CC99FF", "Plum", "FFFFFF", "White"], e = 0; n.length > e; e += 2)t.push({text: n[e + 1], color: n[e]});
            return t
        }

        return e.extend({init: function (e) {
            var t = this;
            t._super(e), t.addClass("colorbutton"), t.on("click", function (e) {
                e.control == t && t.showPicker()
            }), t.hasPopup = !0
        }, showPicker: function () {
            var e = this, r = "", o, a, s, l = e.classPrefix, u;
            if (!e.picker) {
                for (u = i(), r = '<table class="' + l + "grid " + l + 'colorbutton-grid" role="presentation" cellspacing="0"><tbody>', o = Math.ceil(Math.sqrt(u.length)), s = 0; 5 > s; s++) {
                    for (r += "<tr>", a = 0; 8 > a; a++) {
                        var c = u[8 * s + a];
                        r += '<td><div id="' + e._id + "-" + (8 * s + a) + '"' + ' data-mce-color="' + c.color + '"' + ' role="option"' + ' tabIndex="-1"' + ' style="' + (c ? "background-color: #" + c.color : "") + '"' + ' title="' + c.text + '">' + "</div>" + "</td>"
                    }
                    r += "</tr>"
                }
                r += "</tbody></table>", e.picker = new t({popover: !0, autohide: !0, html: r, border: 1, classes: "colorpicker"}).parent(e).renderTo(e.getContainerElm()), e.picker._hasBody = !1, e.picker.reflow(), e.picker.parent(e), e.picker.on("cancel", function (t) {
                    t.control === e.menu && e.focus()
                }), e.picker.on("click", function (t) {
                    var n;
                    (n = t.target.getAttribute("data-mce-color")) && (e.picker.hide(), n = "#" + n, e.showPreview(n), e.fire("select", {value: n}))
                }), e.keyboardNavigation = new n({root: e.picker, items: e.picker.getEl().getElementsByTagName("div"), onCancel: function () {
                    e.picker.hide(), e.fire("cancel")
                }})
            }
            e.picker.show(), e.picker.moveRel(e.getEl(), "bc-tl"), e.keyboardNavigation.focusFirst()
        }, showPreview: function (e) {
            this.getEl("preview").style.backgroundColor = e
        }, renderHtml: function () {
            var e = this, t = e._id, n = e.classPrefix, i = e.settings.icon ? n + "ico " + n + "i-" + e.settings.icon : "", r = e.settings.image ? " style=\"background-image: url('" + e.settings.image + "')\"" : "";
            return'<div id="' + t + '" class="' + e.classes() + '" tabindex="-1">' + '<button role="presentation" tabindex="-1">' + (i ? '<i class="' + i + '"' + r + "></i>" : "") + '<span id="' + t + '-preview" class="' + n + 'preview"></span>' + (e._text ? (i ? " " : "") + e.encode(e._text) : "") + "</button>" + "</div>"
        }})
    }), i(H, [b, g], function (e, n) {
        return e.extend({init: function (e) {
            var i = this;
            i._super(e), i.addClass("combobox"), i.on("click", function (e) {
                for (var t = e.target; t;)t.id && -1 != t.id.indexOf("-open") && i.fire("action"), t = t.parentNode
            }), i.on("keydown", function (e) {
                "INPUT" == e.target.nodeName && 13 == e.keyCode && i.parents().reverse().each(function (n) {
                    return e.preventDefault(), i.fire("change"), n.submit ? (n.submit(), !1) : t
                })
            }), e.placeholder && (i.addClass("placeholder"), i.on("focusin", function () {
                i._hasOnChange || (n.on(i.getEl("inp"), "change", function () {
                    i.fire("change")
                }), i._hasOnChange = !0), i.hasClass("placeholder") && (i.getEl("inp").value = "", i.removeClass("placeholder"))
            }), i.on("focusout", function () {
                0 === i.value().length && (i.getEl("inp").value = e.placeholder, i.addClass("placeholder"))
            }))
        }, value: function (e) {
            var n = this;
            return e !== t ? (n._value = e, n.removeClass("placeholder"), n._rendered && (n.getEl("inp").value = e), n) : n._rendered ? (e = n.getEl("inp").value, e != n.settings.placeholder ? e : "") : n._value
        }, repaint: function () {
            var e = this, t = e.getEl(), i = e.getEl("open"), r = e.layoutRect();
            return i ? n.css(t.firstChild, {width: r.w - i.offsetWidth - 10}) : n.css(t.firstChild, {width: r.w - 10}), e._super(), e
        }, disabled: function (e) {
            var t = this;
            t._super(e), t._rendered && (t.getEl().disabled = e)
        }, focus: function () {
            this.getEl("inp").focus()
        }, postRender: function () {
            var e = this;
            return n.on(this.getEl("inp"), "change", function () {
                e.fire("change")
            }), e._super()
        }, renderHtml: function () {
            var e = this, t = e._id, n = e.settings, i = e.classPrefix, r = n.value || n.placeholder || "", o, a, s = "";
            return o = n.icon ? i + "ico " + i + "i-" + n.icon : "", a = e._text, (o || a) && (s = '<div id="' + t + '-open" class="' + i + "btn " + i + 'open" tabIndex="-1">' + '<button id="' + t + '-action" hidefocus tabindex="-1">' + (o ? '<i class="' + o + '"></i>' : '<i class="' + i + 'caret"></i>') + (a ? (o ? " " : "") + a : "") + "</button>" + "</div>", e.addClass("has-open")), '<div id="' + t + '" class="' + e.classes() + '">' + '<input id="' + t + '-inp" class="' + i + "textbox " + i + 'placeholder" value="' + r + '" hidefocus="true">' + s + "</div>"
        }})
    }), i(P, [E], function (e) {
        return e.extend({Defaults: {layout: "flex", align: "center", defaults: {flex: 1}}, renderHtml: function () {
            var e = this, t = e._layout, n = e.classPrefix;
            return e.addClass("formitem"), t.preRender(e), '<div id="' + e._id + '" class="' + e.classes() + '" hideFocus="1" tabIndex="-1">' + (e.settings.title ? '<div id="' + e._id + '-title" class="' + n + 'title">' + e.settings.title + "</div>" : "") + '<div id="' + e._id + '-body" class="' + e.classes("body") + '">' + (e.settings.html || "") + t.renderHtml(e) + "</div>" + "</div>"
        }})
    }), i(O, [E, P], function (e, n) {
        return e.extend({Defaults: {containerCls: "form", layout: "flex", direction: "column", align: "stretch", flex: 1, padding: 20, labelGap: 30, spacing: 10}, preRender: function () {
            var e = this, i = e.items();
            i.each(function (i) {
                var r, o = i.settings.label;
                o && (r = new n({layout: "flex", autoResize: "overflow", defaults: {flex: 1}, items: [
                    {type: "label", text: o, flex: 0, forId: i._id}
                ]}), r.type = "formitem", i.settings.flex === t && (i.settings.flex = 1), e.replace(i, r), r.add(i))
            })
        }, recalcLabels: function () {
            var e = this, t = 0, n = [], i, r;
            if (e.settings.labelGapCalc !== !1)for (e.items().filter("formitem").each(function (e) {
                var i = e.items()[0], r = i.getEl().clientWidth;
                t = r > t ? r : t, n.push(i)
            }), r = e.settings.labelGap || 0, i = n.length; i--;)n[i].settings.minWidth = t + r
        }, visible: function (e) {
            var t = this._super(e);
            return e === !0 && this._rendered && this.recalcLabels(), t
        }, fromJSON: function (e) {
            var t = this;
            if (e)for (var n in e)t.find("#" + n).value(e[n]);
            return t
        }, toJSON: function () {
            var e = this, n = {};
            return e.find("*").each(function (e) {
                var i = e.name(), r = e.value();
                i && r !== t && (n[i] = r)
            }), n
        }, submit: function () {
            return this.fire("submit", {data: this.toJSON()})
        }, postRender: function () {
            var e = this;
            e._super(), e.recalcLabels(), e.fromJSON(e.settings.data)
        }})
    }), i(L, [O], function (e) {
        return e.extend({Defaults: {containerCls: "fieldset", layout: "flex", direction: "column", align: "stretch", flex: 1, padding: "25 15 5 15", labelGap: 30, spacing: 10, border: 1}, renderHtml: function () {
            var e = this, t = e._layout, n = e.classPrefix;
            return e.preRender(), t.preRender(e), '<fieldset id="' + e._id + '" class="' + e.classes() + '" hideFocus="1" tabIndex="-1">' + (e.settings.title ? '<legend id="' + e._id + '-title" class="' + n + 'fieldset-title">' + e.settings.title + "</legend>" : "") + '<div id="' + e._id + '-body" class="' + e.classes("body") + '">' + (e.settings.html || "") + t.renderHtml(e) + "</div>" + "</fieldset>"
        }})
    }), i(W, [d], function (e) {
        return e.extend({recalc: function (e) {
            var t = e.layoutRect(), n = e.paddingBox();
            e.items().filter(":visible").each(function (e) {
                e.layoutRect({x: n.left, y: n.top, w: t.innerW - n.right - n.left, h: t.innerH - n.top - n.bottom}), e.recalc && e.recalc()
            })
        }})
    }), i(z, [d], function (e) {
        return e.extend({recalc: function (e) {
            var t, n, i, r, o, a, s, l, u, c, d, f, p, h, m, g, v = [], x, y, b, w, _, E, C, R, T, A, k, S, D, F, I, M, N, H, P, O, L, W, z, B, U = Math.max, j = Math.min;
            for (i = e.items().filter(":visible"), r = e.layoutRect(), o = e._paddingBox, a = e.settings, f = a.direction, s = a.align, l = a.pack, u = a.spacing || 0, ("row-reversed" == f || "column-reverse" == f) && (i = i.set(i.toArray().reverse()), f = f.split("-")[0]), "column" == f ? (T = "y", C = "h", R = "minH", A = "maxH", S = "innerH", k = "top", D = "bottom", F = "deltaH", I = "contentH", L = "left", H = "w", M = "x", N = "innerW", P = "minW", O = "maxW", W = "right", z = "deltaW", B = "contentW") : (T = "x", C = "w", R = "minW", A = "maxW", S = "innerW", k = "left", D = "right", F = "deltaW", I = "contentW", L = "top", H = "h", M = "y", N = "innerH", P = "minH", O = "maxH", W = "bottom", z = "deltaH", B = "contentH"), d = r[S] - o[k] - o[k], E = c = 0, t = 0, n = i.length; n > t; t++)p = i[t], h = p.layoutRect(), m = p.settings, g = m.flex, d -= n - 1 > t ? u : 0, g > 0 && (c += g, h[A] && v.push(p), h.flex = g), d -= h[R], x = o[L] + h[P] + o[W], x > E && (E = x);
            if (w = {}, w[R] = 0 > d ? r[R] - d + r[F] : r[S] - d + r[F], w[P] = E + r[z], w[I] = r[S] - d, w[B] = E, w.minW = j(w.minW, r.maxW), w.minH = j(w.minH, r.maxH), w.minW = U(w.minW, r.startMinWidth), w.minH = U(w.minH, r.startMinHeight), !r.autoResize || w.minW == r.minW && w.minH == r.minH) {
                for (b = d / c, t = 0, n = v.length; n > t; t++)p = v[t], h = p.layoutRect(), y = h[A], x = h[R] + Math.ceil(h.flex * b), x > y ? (d -= h[A] - h[R], c -= h.flex, h.flex = 0, h.maxFlexSize = y) : h.maxFlexSize = 0;
                for (b = d / c, _ = o[k], w = {}, 0 === c && ("end" == l ? _ = d + o[k] : "center" == l ? (_ = Math.round(r[S] / 2 - (r[S] - d) / 2) + o[k], 0 > _ && (_ = o[k])) : "justify" == l && (_ = o[k], u = Math.floor(d / (i.length - 1)))), w[M] = o[L], t = 0, n = i.length; n > t; t++)p = i[t], h = p.layoutRect(), x = h.maxFlexSize || h[R], "center" === s ? w[M] = Math.round(r[N] / 2 - h[H] / 2) : "stretch" === s ? (w[H] = U(h[P] || 0, r[N] - o[L] - o[W]), w[M] = o[L]) : "end" === s && (w[M] = r[N] - h[H] - o.top), h.flex > 0 && (x += Math.ceil(h.flex * b)), w[C] = x, w[T] = _, p.layoutRect(w), p.recalc && p.recalc(), _ += x + u
            } else if (w.w = w.minW, w.h = w.minH, e.layoutRect(w), this.recalc(e), null === e._lastRect) {
                var V = e.parent();
                V && (V._lastRect = null, V.recalc())
            }
        }})
    }), i(B, [c], function (e) {
        return e.extend({Defaults: {containerClass: "flow-layout", controlClass: "flow-layout-item", endClass: "break"}, recalc: function (e) {
            e.items().filter(":visible").each(function (e) {
                e.recalc && e.recalc()
            })
        }})
    }), i(U, [b, g], function (e, n) {
        function i() {
        }

        return e.extend({Defaults: {minWidth: 1}, init: function (e) {
            var t = this;
            t._super(e), t._sortBy = e.sortBy, t._sortOrder = "asc" === e.sortDir, t._selected = [], t._data = []
        }, postRender: function () {
            var e = this, t = e.settings, i = e.classPrefix;
            e._super(), e.on("keyup", function (t) {
                var n = t.keyCode;
                27 == n && e.cancelEdit(), 13 == n && e.applyEdit()
            }), e.on("focusout", function (t) {
                "text" == t.target.type && e.cancelEdit()
            }), e.on("click", function (r) {
                function o(e) {
                    for (var t = e.parentNode.childNodes, n = t.length; n--;)if (t[n] === e)return n;
                    return-1
                }

                var a = r.target, s, l, u, c, d, f, p, h;
                do if (n.hasClass(a, i + "grid-cell") && (p = !0), "TD" == a.nodeName) {
                    s = a;
                    break
                } while (a = a.parentNode);
                if (s)if (f = t.columns, l = o(s), u = o(s.parentNode), d = f[l], n.hasClass(s, i + "grid-head-item"))d.sorting !== !1 && (h = e._sortOrder, h === e._sortOrder && (h = e._sortBy == d.name ? !h : !0), e._sortBy = d.name, e._sortOrder = h, e.update()), "checkbox" == d.type && (e.selected().length > 0 ? e.unselect(0, e.data().length) : e.select(0, e.data().length)); else {
                    c = e.data()[u];
                    var m = e.fire("cellclick", {row: c, value: c[f[l].name], rowIndex: u, cellIndex: l, inValue: !!p});
                    m.isDefaultPrevented() || (r.shiftKey ? e.select(e._focusIndex, u) : r.ctrlKey ? e[e._selected[u] ? "unselect" : "select"](u) : "checkbox" == d.type ? e[e._selected[u] ? "unselect" : "select"](u) : e.unselect(0, e._data.length).select(u))
                }
            }), e.data(e.settings.data)
        }, repaint: function () {
            var e = this, t = e.layoutRect(), n, i;
            e._super(), n = e.getEl("body").style, n.width = t.innerW + "px", n.height = t.innerH - e.getEl("head").offsetHeight + "px", i = e.getEl("thead").offsetWidth, i && (e.getEl("head").style.width = i + "px")
        }, data: function (e) {
            var n = this;
            return e !== t ? (n._data = e, n._selected = {}, n.update(), n.repaint(), n) : n._data
        }, update: function () {
            function e(e, t, n) {
                function i(e, i) {
                    return e = parseFloat(e[t]), i = parseFloat(i[t]), e = isNaN(e) ? 0 : e, i = isNaN(i) ? 0 : i, n ? e - i : i - e
                }

                function r(e, i) {
                    return e = ("" + e[t]).toLowerCase(), i = ("" + i[t]).toLowerCase(), n ? e.localeCompare(i) : i.localeCompare(e)
                }

                return e.length && (e = e.sort("number" == typeof e[0][t] ? i : r)), e
            }

            var t = this, i, r, o, a, s, l, u, c, d, f = t.classPrefix, p, h, m = t.settings, g;
            for (r = m.columns, m.sortBy && (s = e(t._data, t._sortBy, t._sortOrder)), s = t.fire("beforeUpdate", {data: s}).data, i = [], c = t.getEl("headr"), o = 0; c.childNodes.length > o; o++)n.removeClass(c.childNodes[o].lastChild.lastChild, f + "up"), n.removeClass(c.childNodes[o].lastChild.lastChild, f + "down"), r[o].name == t._sortBy && (g = o);
            for (t._sortOrder ? n.addClass(c.childNodes[g].lastChild.lastChild, f + "up") : n.addClass(c.childNodes[g].lastChild.lastChild, f + "down"), i.push('<table><tbody id="' + t._id + '-tbody">'), a = 0; s.length > a; a++) {
                for (h = s[a], i.push('<tr class="' + f + "grid-row", a % 2 ? " " + f + "grid-odd" : "", '">'), o = 0; r.length > o; o++) {
                    var v = {row: h, value: h[r[o].name || o], icon: r[o].icon};
                    u = r[o].filter, u && u(v), p = v.icon ? '<i class="' + f + "ico " + f + "i-" + v.icon + '" unselectable="on"></i>' : "", i.push('<td><div class="' + f + "txt " + f + 'grid-cell">', p, '<span class="' + f + "txt " + f + 'reset" title="' + v.value + '">', v.value || "&nbsp;", "</span></div></td>")
                }
                i.push("</tr>")
            }
            return i.push("</tbody></table>"), d = document.createElement("div"), d.innerHTML = i.join(""), l = t.getEl("tbody", !0), l.parentNode.replaceChild(d.firstChild.firstChild, l), t._data = s, t._selected = {}, t.updateCheckedState(), t
        }, selected: function () {
            var e = this, t = e._data, n, i, r = e._selected, o = [];
            for (n = 0, i = t.length; i > n; n++)r[n] && o.push(t[n]);
            return o
        }, selectable: function () {
            var e = this, t = e._data, n, i, r = [];
            for (n = 0, i = t.length; i > n; n++)t[n].isParent || r.push(t[n]);
            return r
        }, isSelected: function (e) {
            return!!this._selected[e]
        }, unselect: function (e, t) {
            return this.setRowState(e, t, !1)
        }, select: function (e, t) {
            return this.setRowState(e, t, !0)
        }, setRowState: function (e, i, r) {
            var o = this, a, s, l, u, c, d = o.classPrefix;
            for (e === t && i === t ? (e = 0, i = o._data.length) : i === t && (i = e), c = o._data.length - 1, e = e ? e > c ? c : e : 0, i = i ? i > c ? c : i : 0, l = o._selected, l || (o._selected = l = {}), o._rendered && (s = o.getEl("tbody", !0).rows), r ? o._focusIndex = e : delete o._focusIndex, e > i && (u = e, e = i, i = u), a = e; i >= a; a++)o._data[a] && !o._data[a].isParent && (s && n.toggleClass(s[a], d + "checked", r), l[a] = r);
            return o.fire("select", {selected: o.selected()}), o.updateCheckedState(), o
        }, updateCheckedState: function () {
            var e = this, t, i, r = e.classPrefix;
            for (i = e.settings.columns, t = 0; i.length > t; t++)if ("checkbox" == i[t].type) {
                var o = e.getEl("headr").childNodes[t].firstChild.firstChild;
                e.selected().length && e.selected().length == e.selectable().length ? (n.removeClass(o, r + "inter"), n.addClass(o, r + "checked")) : e.selected().length ? (n.removeClass(o, r + "checked"), n.addClass(o, r + "inter")) : (n.removeClass(o, r + "checked"), n.removeClass(o, r + "inter"))
            }
        }, edit: function (e, t, r, o) {
            var a = this, s = a.data(), l = a.settings.columns, u, c, d, f, p;
            a.cancelEdit(), p = s[e][l[t].name], p = a.fire("beforeEdit", {value: p}).value, u = a.getEl("tbody", !0), c = u.childNodes[e].childNodes[t].firstChild, c.lastChild.style.display = "none", c.appendChild(n.createFragment('<span id="' + a._id + '-edit" class="mce-grid-edit"><input type="text" value="' + p + '" class="mce-reset" /></span>')), d = a.getEl("edit", !0), f = d.firstChild, f.focus(), a.applyEdit = function () {
                o = i, a.cancelEdit(), c.lastChild.innerText = s[e][t] = f.value, r && r({value: f.value})
            }, a.cancelEdit = function () {
                a.cancelEdit = a.applyEdit = i, d.previousSibling.style.display = "", d.parentNode.removeChild(d), o && o()
            }
        }, cancelEdit: i, applyEdit: i, renderHtml: function () {
            var e = this, t = [], n = e.settings, i, r = n.columns, o, a, s, l, u = e.classPrefix;
            for (o = 0; r.length > o; o++)a = r[o].width, "number" == typeof a && (a > 0 && 1 > a ? a = 100 * a + "%" : a += "px"), r[o].width = a;
            for (t.push('<div id="' + e._id + '" class="' + e.classes() + " " + u + 'grid">'), t.push('<div class="' + u + 'grid-head" unselectable="true"><table id="' + e._id + '-head" class="' + u + 'grid-head"><tbody><tr id="' + e._id + '-headr">'), o = 0; r.length > o; o++)i = r[o], a = i.width, i.sorting !== !1 && "checkbox" != i.type && (s = '<i class="' + u + 'caret"></i>'), l = e.encode(i.title) || "&nbsp;", "checkbox" == i.type && (l = '<i class="' + u + "ico " + u + 'i-checkbox" unselectable="on"></i>' + l), t.push('<td class="' + u + 'grid-head-item"', a ? ' style="width: ' + a + '"' : "", '><div class="' + u + "txt " + u + 'grid-cell">', l, s, "</div></td>");
            for (t.push("</tr></tbody></table></div>"), t.push('<div id="' + e._id + '-body" class="' + u + 'grid-body"><table>'), t.push('<thead id="' + e._id + '-thead">'), o = 0; r.length > o; o++)a = r[o].width, t.push("<td", a ? ' style="width: ' + r[o].width + '"' : "", '><div class="' + u + "txt " + u + 'grid-cell">', o, "</div></td>");
            return t.push('</thead><tbody id="' + e._id + '-tbody"></tbody></table></div></div>'), t.join("")
        }})
    }), i(j, [d], function (e) {
        return e.extend({recalc: function (e) {
            var t = e.settings, n, i, r, o, a, s, l, u, c, d, f, p, h, m, g, v, x, y, b, w, _, E, C = [], R = [], T, A, k, S, D, F;
            for (t = e.settings, r = e.items().filter(":visible"), o = e.layoutRect(), i = t.columns || Math.ceil(Math.sqrt(r.length)), n = Math.ceil(r.length / i), x = t.spacingH || t.spacing || 0, y = t.spacingV || t.spacing || 0, b = t.alignH || t.align, w = t.alignV || t.align, g = e._paddingBox, d = 0; i > d; d++)C.push(0);
            for (f = 0; n > f; f++)R.push(0);
            for (f = 0; n > f; f++)for (d = 0; i > d && (c = r[f * i + d], c); d++)u = c.layoutRect(), T = u.minW, A = u.minH, C[d] = T > C[d] ? T : C[d], R[f] = A > R[f] ? A : R[f];
            for (D = o.innerW - g.left - g.right, _ = 0, d = 0; i > d; d++)_ += C[d] + (d > 0 ? x : 0), D -= (d > 0 ? x : 0) + C[d];
            for (F = o.innerH - g.top - g.bottom, E = 0, f = 0; n > f; f++)E += R[f] + (f > 0 ? y : 0), F -= (f > 0 ? y : 0) + R[f];
            if (_ += g.left + g.right, E += g.top + g.bottom, l = {}, l.minW = _ + (o.w - o.innerW), l.minH = E + (o.h - o.innerH), l.contentW = l.minW - o.deltaW, l.contentH = l.minH - o.deltaH, l.minW = Math.min(l.minW, o.maxW), l.minH = Math.min(l.minH, o.maxH), l.minW = Math.max(l.minW, o.startMinWidth), l.minH = Math.max(l.minH, o.startMinHeight), !o.autoResize || l.minW == o.minW && l.minH == o.minH) {
                o.autoResize && (l = e.layoutRect(l), l.contentW = l.minW - o.deltaW, l.contentH = l.minH - o.deltaH);
                var I;
                I = "start" == t.packV ? 0 : F > 0 ? Math.floor(F / n) : 0;
                var M = 0, N = t.flexWidths;
                if (N)for (d = 0; N.length > d; d++)M += N[d]; else M = i;
                var H = D / M;
                for (d = 0; i > d; d++)C[d] += N ? Math.ceil(N[d] * H) : H;
                for (h = g.top, f = 0; n > f; f++) {
                    for (p = g.left, s = R[f] + I, d = 0; i > d && (c = r[f * i + d], c); d++)m = c.settings, u = c.layoutRect(), a = C[d], k = S = 0, u.x = p, u.y = h, v = m.alignH || b, "center" == v ? u.x = p + a / 2 - u.w / 2 : "right" == v ? u.x = p + a - u.w : "stretch" == v && (u.w = a), v = m.alignV || w, "center" == v ? u.y = h + s / 2 - u.h / 2 : "bottom" == v ? u.y = h + s - u.h : "stretch" == v && (u.h = s), c.layoutRect(u), p += a + x, c.recalc && c.recalc();
                    h += s + y
                }
            } else if (l.w = l.minW, l.h = l.minH, e.layoutRect(l), this.recalc(e), null === e._lastRect) {
                var P = e.parent();
                P && (P._lastRect = null, P.recalc())
            }
        }})
    }), i(V, [b], function (e) {
        return e.extend({renderHtml: function () {
            var e = this;
            return e.addClass("iframe"), e.canFocus = !1, '<iframe id="' + e._id + '" class="' + e.classes() + '" tabindex="-1" src="' + (e.settings.url || "javascript:''") + '"></iframe>'
        }, src: function (e) {
            this.getEl().src = e
        }, html: function (e) {
            return this.getEl().contentWindow.document.body.innerHTML = e, this
        }})
    }), i(q, [b], function (e) {
        return e.extend({init: function (e) {
            var t = this;
            t._super(e), t.addClass("image"), t.on("click", function (e) {
                -1 != e.target.className.indexOf("checkbox") && t.toggleClass("checked", !0)
            })
        }, renderHtml: function () {
            var e = this, t = e._id, n = e.classPrefix, i = e.settings, r = i.icon;
            return'<div id="' + t + '" class="' + e.classes() + '">' + (r ? '<i class="' + n + "ico " + n + "i-" + r + '"></i>' : "") + (i.url ? '<img src="' + i.url + '" />' : "") + '<div class="' + n + 'info">' + (i.selectable ? '<i class="' + n + "ico " + n + 'i-checkbox"></i>' : "") + e.settings.text + "</div>" + "</div>"
        }})
    }), i(X, [], function () {
        function e(e) {
            return"[object Number]" == Object.prototype.toString.call(e)
        }

        function n(e, t, n) {
            var i = e.createShader(t);
            if (e.shaderSource(i, n), e.compileShader(i), !e.getShaderParameter(i, e.COMPILE_STATUS))throw"compile error: " + e.getShaderInfoLog(i);
            return i
        }

        var i = "attribute vec2 vertex;attribute vec2 _texCoord;varying vec2 texCoord;void main() {texCoord = _texCoord;gl_Position = vec4(vertex * 2.0 - 1.0, 0.0, 1.0);}", r = "uniform sampler2D texture;varying vec2 texCoord;void main() {gl_FragColor = texture2D(texture, texCoord);}";
        return function (o, a, s) {
            function l(t) {
                o.useProgram(p);
                for (var n in t)if (t.hasOwnProperty(n)) {
                    var i = o.getUniformLocation(p, n);
                    if (null !== i) {
                        var r = t[n];
                        if (r.length)switch (r.length) {
                            case 1:
                                o.uniform1fv(i, new Float32Array(r));
                                break;
                            case 2:
                                o.uniform2fv(i, new Float32Array(r));
                                break;
                            case 3:
                                o.uniform3fv(i, new Float32Array(r));
                                break;
                            case 4:
                                o.uniform4fv(i, new Float32Array(r));
                                break;
                            case 9:
                                o.uniformMatrix3fv(i, !1, new Float32Array(r));
                                break;
                            case 16:
                                o.uniformMatrix4fv(i, !1, new Float32Array(r));
                                break;
                            default:
                                throw"dont't know how to load uniform \"" + n + '" of length ' + r.length
                        } else {
                            if (!e(r))throw'attempted to set uniform "' + n + '" to invalid value ' + ("" + (r || "undefined"));
                            o.uniform1f(i, r)
                        }
                    }
                }
                return f
            }

            function u(e) {
                o.useProgram(p);
                for (var t in e)e.hasOwnProperty(t) && o.uniform1i(o.getUniformLocation(p, t), e[t]);
                return f
            }

            function c(e, n, i, r) {
                var a = o.getParameter(o.VIEWPORT);
                return n = n !== t ? (n - a[1]) / a[3] : 0, e = e !== t ? (e - a[0]) / a[2] : 0, i = i !== t ? (i - a[0]) / a[2] : 1, r = r !== t ? (r - a[1]) / a[3] : 1, o.vertexBuffer || (o.vertexBuffer = o.createBuffer()), o.bindBuffer(o.ARRAY_BUFFER, o.vertexBuffer), o.bufferData(o.ARRAY_BUFFER, new Float32Array([e, n, e, r, i, n, i, r]), o.STATIC_DRAW), o.texCoordBuffer || (o.texCoordBuffer = o.createBuffer(), o.bindBuffer(o.ARRAY_BUFFER, o.texCoordBuffer), o.bufferData(o.ARRAY_BUFFER, new Float32Array([0, 0, 0, 1, 1, 0, 1, 1]), o.STATIC_DRAW)), h || (h = o.getAttribLocation(p, "vertex"), o.enableVertexAttribArray(h)), m || (m = o.getAttribLocation(p, "_texCoord"), o.enableVertexAttribArray(m)), o.useProgram(p), o.bindBuffer(o.ARRAY_BUFFER, o.vertexBuffer), o.vertexAttribPointer(h, 2, o.FLOAT, !1, 0, 0), o.bindBuffer(o.ARRAY_BUFFER, o.texCoordBuffer), o.vertexAttribPointer(m, 2, o.FLOAT, !1, 0, 0), o.drawArrays(o.TRIANGLE_STRIP, 0, 4), f
            }

            function d() {
                o.deleteProgram(p), p = null
            }

            var f = this, p, h, m;
            if (p = o.createProgram(), a = a || i, s = s || r, s = "precision highp float;" + s, o.attachShader(p, n(o, o.VERTEX_SHADER, a)), o.attachShader(p, n(o, o.FRAGMENT_SHADER, s)), o.linkProgram(p), !o.getProgramParameter(p, o.LINK_STATUS))throw"link error: " + o.getProgramInfoLog(p);
            f.uniforms = l, f.textures = u, f.drawRect = c, f.destroy = d
        }
    }), i(G, [], function () {
        return function (e, t, n, i, r) {
            function o(o) {
                t = o.width || o.videoWidth, n = o.height || o.videoHeight, e.bindTexture(e.TEXTURE_2D, u), e.texImage2D(e.TEXTURE_2D, 0, i, i, r, o)
            }

            function a() {
                e.deleteTexture(u), u = null
            }

            function s(t) {
                e.activeTexture(e.TEXTURE0 + (t || 0)), e.bindTexture(e.TEXTURE_2D, u)
            }

            function l(i) {
                e.framebuffer = e.framebuffer || e.createFramebuffer(), e.bindFramebuffer(e.FRAMEBUFFER, e.framebuffer), e.framebufferTexture2D(e.FRAMEBUFFER, e.COLOR_ATTACHMENT0, e.TEXTURE_2D, u, 0), e.viewport(0, 0, t, n), i(), e.bindFramebuffer(e.FRAMEBUFFER, null)
            }

            var u = e.createTexture();
            return i = i || e.RGBA, r = r || e.UNSIGNED_BYTE, e.bindTexture(e.TEXTURE_2D, u), e.texParameteri(e.TEXTURE_2D, e.TEXTURE_MAG_FILTER, e.LINEAR), e.texParameteri(e.TEXTURE_2D, e.TEXTURE_MIN_FILTER, e.LINEAR), e.texParameteri(e.TEXTURE_2D, e.TEXTURE_WRAP_S, e.CLAMP_TO_EDGE), e.texParameteri(e.TEXTURE_2D, e.TEXTURE_WRAP_T, e.CLAMP_TO_EDGE), t && n && e.texImage2D(e.TEXTURE_2D, 0, i, t, n, 0, i, r, null), {loadContentsOf: o, destroy: a, use: s, drawTo: l, width: t, height: n}
        }
    }), i(Y, [X, G], function (e, n) {
        var i = {vibrance: "uniform sampler2D texture;\nuniform float adjust;\nvarying vec2 texCoord;\n\nvoid main() {\n    vec4 color = texture2D(texture, texCoord);\n    float average = (color.r + color.g + color.b) / 3.0;\n    float mx = max(color.r, max(color.g, color.b));\n    float amt = (mx - average) * (-adjust * 3.0);\n\n    color.rgb = mix(color.rgb, vec3(mx), amt);\n\n    gl_FragColor = color;\n}\n", triangleblur: "uniform sampler2D texture;\nuniform vec2 delta;\nvarying vec2 texCoord;\n\nfloat random(vec3 scale, float seed) {\n	/* use the fragment position for a different seed per-pixel */\n	return fract(sin(dot(gl_FragCoord.xyz + seed, scale)) * 43758.5453 + seed);\n}\n\nvoid main() {\n	vec4 color = vec4(0.0);\n	float total = 0.0;\n\n	/* randomize the lookup values to hide the fixed number of samples */\n	float offset = random(vec3(12.9898, 78.233, 151.7182), 0.0);\n\n	for (float t = -30.0; t <= 30.0; t++) {\n		float percent = (t + offset - 0.5) / 30.0;\n		float weight = 1.0 - abs(percent);\n		vec4 sample = texture2D(texture, texCoord + delta * percent);\n\n		/* switch to pre-multiplied alpha to correctly blur transparent images */\n		sample.rgb *= sample.a;\n\n		color += sample * weight;\n		total += weight;\n	}\n\n	gl_FragColor = color / total;\n\n	/* switch back from pre-multiplied alpha */\n	gl_FragColor.rgb /= gl_FragColor.a + 0.00001;\n}\n\n", sepia: "uniform sampler2D texture;\r\nuniform float adjust;\r\nvarying vec2 texCoord;\r\n\r\nvoid main() {\r\n	vec4 color = texture2D(texture, texCoord);\r\n\r\n	float r = color.r;\r\n	float g = color.g;\r\n	float b = color.b;\r\n\r\n	color.r = min(1.0, (r * (1.0 - (0.607 * adjust))) + (g * (0.769 * adjust)) + (b * (0.189 * adjust)));\r\n	color.g = min(1.0, (r * 0.349 * adjust) + (g * (1.0 - (0.314 * adjust))) + (b * 0.168 * adjust));\r\n	color.b = min(1.0, (r * 0.272 * adjust) + (g * 0.534 * adjust) + (b * (1.0 - (0.869 * adjust))));\r\n\r\n	gl_FragColor = color;\r\n}", saturate: "uniform sampler2D texture;\r\nuniform float adjust;\r\nvarying vec2 texCoord;\r\n\r\nvoid main() {\r\n	vec4 color = texture2D(texture, texCoord);\r\n	float average = (color.r + color.g + color.b) / 3.0;\r\n\r\n	color.rgb += (average - color.rgb) * (adjust * -1.0);\r\n\r\n	gl_FragColor = color;\r\n}\r\n", invert: "uniform sampler2D texture;\r\nvarying vec2 texCoord;\r\n\r\nvoid main() {\r\n	vec4 sample = texture2D(texture, texCoord);\r\n\r\n	sample.r = 1.0 - sample.r;\r\n	sample.g = 1.0 - sample.g;\r\n	sample.b = 1.0 - sample.b;\r\n\r\n	gl_FragColor = sample;\r\n}\r\n", hue: "uniform sampler2D texture;\nuniform float adjust;\nvarying vec2 texCoord;\n\nvoid main() {\n	vec4 color = texture2D(texture, texCoord);\n\n	/* hue adjustment, wolfram alpha: RotationTransform[angle, {1, 1, 1}][{x, y, z}] */\n	float angle = adjust * 3.14159265;\n	float s = sin(angle), c = cos(angle);\n	vec3 weights = (vec3(2.0 * c, -sqrt(3.0) * s - c, sqrt(3.0) * s - c) + 1.0) / 3.0;\n	float len = length(color.rgb);\n	color.rgb = vec3(\n		dot(color.rgb, weights.xyz),\n		dot(color.rgb, weights.zxy),\n		dot(color.rgb, weights.yzx)\n	);\n\n	gl_FragColor = color;\n}\n", grayscale: "uniform sampler2D texture;\r\nvarying vec2 texCoord;\r\n\r\nvoid main() {\r\n	vec4 sample = texture2D(texture, texCoord);\r\n\r\n	sample.r = sample.g = sample.b = (sample.r + sample.g + sample.b) / 3.0;\r\n\r\n	gl_FragColor = sample;\r\n}\r\n", gamma: "uniform sampler2D texture;\r\nvarying vec2 texCoord;\r\nuniform float adjust;\r\n\r\nvoid main() {\r\n	vec4 sample = texture2D(texture, texCoord);\r\n\r\n	sample.r = pow(sample.r / 1.0, adjust) * 1.0;\r\n	sample.g = pow(sample.g / 1.0, adjust) * 1.0;\r\n	sample.b = pow(sample.b / 1.0, adjust) * 1.0;\r\n\r\n	gl_FragColor = sample;\r\n}\r\n", flippedshader: "uniform sampler2D texture;\r\nvarying vec2 texCoord;\r\n\r\nvoid main() {\r\n	gl_FragColor = texture2D(texture, vec2(texCoord.x, 1.0 - texCoord.y));\r\n}\r\n", exposure: "uniform sampler2D texture;\nvarying vec2 texCoord;\nuniform float adjust;\n\nvoid main() {\n	vec4 sample = texture2D(texture, texCoord);\n\n	sample.rgb = (1.0 - exp(-(sample.rgb) * adjust));\n\n	gl_FragColor = sample;\n}\n", convolute: "uniform sampler2D texture;\r\nuniform mat3 kernel;\r\nvarying vec2 texCoord;\r\nuniform vec2 textureSize;\r\n\r\nvoid main() {\r\n	vec2 onePixel = vec2(1.0, 1.0) / textureSize;\r\n	vec4 colorSum;\r\n\r\n	float kernelWeight;\r\n	for (int y = 0; y < 3; y++) {\r\n		for (int x = 0; x < 3; x++) {\r\n			colorSum += texture2D(texture, texCoord + onePixel * vec2(1 - y, 1 - x)) * kernel[x][y];\r\n			kernelWeight += kernel[y][x];\r\n		}\r\n	}\r\n\r\n	if (kernelWeight <= 0.0) {\r\n		kernelWeight = 1.0;\r\n	}\r\n\r\n	gl_FragColor = vec4((colorSum / kernelWeight).rgb, 1);\r\n}\r\n", contrast: "uniform sampler2D texture;\r\nvarying vec2 texCoord;\r\nuniform float adjust;\r\n\r\nvoid main() {\r\n	vec4 color = texture2D(texture, texCoord);\r\n\r\n	if (adjust > 0.0) {\r\n		color.rgb = (color.rgb - 0.5) / (1.0 - adjust) + 0.5;\r\n	} else {\r\n		color.rgb = (color.rgb - 0.5) * (1.0 + adjust) + 0.5;\r\n	}\r\n\r\n	gl_FragColor = color;\r\n}\r\n", colorize: "uniform sampler2D texture;\r\nvarying vec2 texCoord;\r\nuniform float adjustR, adjustG, adjustB;\r\n\r\nvoid main() {\r\n	vec4 sample = texture2D(texture, texCoord);\r\n\r\n	sample.r *= adjustR;\r\n	sample.g *= adjustG;\r\n	sample.b *= adjustB;\r\n\r\n	gl_FragColor = sample;\r\n}\r\n", brightness: "uniform sampler2D texture;\r\nvarying vec2 texCoord;\r\nuniform float adjust;\r\n\r\nvoid main() {\r\n	vec4 color = texture2D(texture, texCoord);\r\n\r\n	color.rgb += adjust;\r\n\r\n	gl_FragColor = color;\r\n}\r\n"};
        return function (r) {
            function o(e, t) {
                V[e] = t
            }

            function a(e, t) {
                O.width = e, O.height = t
            }

            function s(e) {
                a(e.naturalWidth || e.width, e.naturalHeight || e.height), L.drawImage(e, 0, 0)
            }

            function l(e, t) {
                var n = new Image;
                n.onload = function () {
                    n.onload = null, s(n), t && t.call(P), S()
                }, n.src = e
            }

            function u(e, t) {
                t.width = e.width, t.height = e.height, t.getContext("2d").drawImage(e, 0, 0)
            }

            function c(e) {
                q = !e
            }

            function d() {
                q || (B.length > U && B.splice(U, B.length), r.undoLevels && U >= r.undoLevels && (B.shift(), U--), B.push(L.getImageData(0, 0, O.width, O.height)), U = B.length)
            }

            function f() {
                return j.getElementById(r.image)
            }

            function p(e) {
                var t = f();
                t.onload = function () {
                    t.onload = null, t.style.display = "", e()
                }, t.src = D()
            }

            function h(e) {
                return function () {
                    d(), L.save(), e.apply(P, arguments), L.restore(), L.setTransform(1, 0, 0, 1, 0, 0), p(S)
                }
            }

            function m() {
                return U > 0
            }

            function g() {
                return B.length - 1 > U
            }

            function v(e) {
                var n;
                e = e !== t ? e : B.length - 1, n = B[e], a(n.width, n.height), L.putImageData(n, 0, 0)
            }

            function x(e, t) {
                v(e), U = e, t && B.splice(e)
            }

            function y(e) {
                m() && (U == B.length && B.push(L.getImageData(0, 0, O.width, O.height)), x(U - 1, e), p(S))
            }

            function b(e) {
                g() && (x(U + 1, e), p(S))
            }

            function w() {
                m() && (x(0, !0), p(S))
            }

            function _(e) {
                var t = 0, n = 0, i, r;
                e = 0 > e ? 360 + e : e, 90 == e || 270 == e ? (i = O.height, r = O.width) : (i = O.width, r = O.height), (90 == e || 180 == e) && (t = i), (270 == e || 180 == e) && (n = r), u(O, W), a(i, r), L.translate(t, n), L.rotate(e * Math.PI / 180), L.drawImage(W, 0, 0)
            }

            function E(e) {
                u(O, W), a(e.w, e.h), L.drawImage(W, 0, 0, e.w, e.h)
            }

            function C(e) {
                "v" == e ? (L.scale(1, -1), L.drawImage(O, 0, -O.height)) : (L.scale(-1, 1), L.drawImage(O, -O.width, 0))
            }

            function R(e) {
                u(O, W), a(e.w, e.h), L.drawImage(W, -e.x, -e.y)
            }

            function T(e, t, n, i, o, a) {
                function s() {
                    var t = d[g++], o;
                    t ? (e(t.x, t.y, t.x + t.w, t.y + t.h, f.data, f.width, f.height, p ? p.data : null), o = (new Date).getTime(), 1e3 > o - h ? s() : (h = o, window.setTimeout(s, 0), r.onprogress && r.onprogress.call(P, g / d.length))) : (L.putImageData(p || f, n, i), r.onprogress && r.onprogress.call(P, 1), S())
                }

                var l, u, c = 600, d = [], f, p, h, m, g = 0;
                for (n = n || 0, i = i || 0, o = o || O.width, a = a || O.height, u = i; a > u; u += c)for (l = n; o > l; l += c)d.push({x: l, y: u, w: l + c > o ? o - l : c, h: u + c > a ? a - u : c});
                h = m = (new Date).getTime(), f = L.getImageData(n, i, o, a), t && (p = L.getImageData(n, i, o, a)), s()
            }

            function A(e) {
                T(function (t, n, i, r, o, a, s, l) {
                    var u, c, d, f, p, h, m, g, v, x, y, b, w;
                    for (u = Math.round(Math.sqrt(e.length)), c = Math.floor(u / 2), f = n; r > f; f++)for (d = t; i > d; d++) {
                        for (p = h = m = 0, v = 0; u > v; v++)for (g = 0; u > g; g++)x = d + g - c, y = f + v - c, x = 0 > x ? 0 : x > a ? a : x, y = 0 > y ? 0 : y > s ? s : y, b = 4 * (y * a + x), w = e[v * u + g], p += o[b] * w, h += o[b + 1] * w, m += o[b + 2] * w;
                        b = 4 * (f * a + d), l[b] = p > 255 ? 255 : 0 > p ? 0 : p, l[b + 1] = h > 255 ? 255 : 0 > h ? 0 : h, l[b + 2] = m > 255 ? 255 : 0 > m ? 0 : m
                    }
                }, !0)
            }

            function k() {
                return{w: O.width, h: O.height}
            }

            function S() {
                r.onchange && r.onchange.call(this)
            }

            function D(e) {
                var t, n = {jpg: "image/jpeg", jpeg: "image/jpeg", png: "image/png"};
                return e = e || "png", t = n[e.substr(e.lastIndexOf(".") + 1).toLowerCase()], O.toDataURL(t)
            }

            function F() {
                var t, r;
                X.width = t = O.width, X.height = r = O.height, X.style.cssText = O.style.cssText, O.style.display = "none", K = new e(G, null, i.flippedshader), Y = new n(G, t, r), $ = new n(G, t, r), J = new n(G, t, r), Y.loadContentsOf(O)
            }

            function I(e, t, n, i) {
                (n || Y).use(), (i || $).drawTo(function () {
                    e.uniforms(t).drawRect()
                })
            }

            function M() {
                $.use(), K.drawRect()
            }

            function N() {
                function t(t, n) {
                    var r = Z[t] = Z[t] || new e(G, null, i[t]);
                    I.call(this, r, n), M()
                }

                function n(e) {
                    o(e, function (n) {
                        t(e, {adjust: n})
                    })
                }

                function r(e, n) {
                    o(e, function () {
                        t("convolute", {textureSize: [Y.width, Y.height], kernel: n})
                    })
                }

                if (G) {
                    for (var a in i)n(a);
                    r("sharpen", [0, -1, 0, -1, 5, -1, 0, -1, 0]), r("emboss", [-2, -1, 0, -1, 1, 1, 0, 1, 2]), o("colorize", function (e, n, i) {
                        t("colorize", {adjustR: e, adjustG: n, adjustB: i})
                    }), o("triangleblur", function (t) {
                        var n = Z.triangleblur = Z.triangleblur || new e(G, null, i.triangleblur);
                        I.call(this, n, {delta: [t / Y.width, 0]}, Y, J), I.call(this, n, {delta: [0, t / Y.height]}, J, $), M()
                    })
                }
            }

            function H(e) {
                function t() {
                    n ? G || v() : (n = !0, f().style.display = "none", G ? (F(), X.style.display = "") : (d(), O.style.display = ""), e.init && e.init())
                }

                var n;
                return{live: function () {
                    var n = arguments;
                    G && (t(), e.liveGPU.apply(this, n))
                }, apply: function () {
                    var n = arguments;
                    t(), G ? e.liveGPU.apply(this, n) : e.live.apply(this, n)
                }, save: function () {
                    function e() {
                        var e = $.width, t = $.height, n = new Uint8Array(4 * e * t);
                        return $.drawTo(function () {
                            G.readPixels(0, 0, e, t, G.RGBA, G.UNSIGNED_BYTE, n)
                        }), n
                    }

                    if (n = !1, G) {
                        for (var t = e(), i = L.createImageData($.width, $.height), r = i.data, o = t.length; o--;)r[o] = t[o];
                        d(), L.putImageData(i, 0, 0), O.style.display = "", X.style.display = "none"
                    }
                    p(function () {
                        O.style.display = "none", S()
                    })
                }, cancel: function () {
                    n = !1, f().style.display = "", O.style.display = "none", X.style.display = "none", G || (x(U - 1), S())
                }}
            }

            var P = this, O, L, W, z, B = [], U = 0, j = document, V = {}, q, X, G, Y, $, J, K, Z = {};
            if (O = j.createElement("canvas"), O.getContext) {
                L = O.getContext("2d"), O.style.display = "none", j.getElementById(r.viewport).appendChild(O), W = j.createElement("canvas"), z = W.getContext("2d"), X = j.createElement("canvas"), X.style.display = "none";
                try {
                    G = X.getContext("experimental-webgl", {premultipliedAlpha: !1}), window.opera && (G = null), G && j.getElementById(r.viewport).appendChild(X)
                } catch (Q) {
                }
            }
            return o("grayscale", function () {
                T(function (e, t, n, i, r, o) {
                    var a, s, l;
                    for (s = t; i > s; s++)for (a = e; n > a; a++)l = 4 * (s * o + a), r[l] = r[l + 1] = r[l + 2] = (r[l] + r[l + 1] + r[l + 2]) / 3
                })
            }), o("redeye", function (e, t, n) {
                var i, r, o, a, s, l, u, c, d, f;
                for (e = 0 | e, t = 0 | t, n = 0 | n, i = L.getImageData(e - n, t - n, 2 * n, 2 * n), r = i.data, d = i.width, a = -n; n >= a; a++)for (o = -n; n >= o; o++)n * n >= o * o + a * a && (f = 4 * ((n + a) * d + (n + o)), l = r[f], u = r[f + 1], c = r[f + 2], s = u || c ? 2 * l / (u + c) : l ? 2 : 0, s > 1.6 && (r[f] = u || c ? (u + c) / 2 : 0));
                L.putImageData(i, e - n, t - n)
            }), o("invert", function () {
                T(function (e, t, n, i, r, o) {
                    var a, s, l;
                    for (s = t; i > s; s++)for (a = e; n > a; a++)l = 4 * (s * o + a), r[l] = 255 - r[l], r[l + 1] = 255 - r[l + 1], r[l + 2] = 255 - r[l + 2]
                })
            }), o("gamma", function (e) {
                for (var t = 256, n = []; t--;)n[t] = 255 * Math.pow(t / 255, e);
                T(function (e, t, i, r, o, a) {
                    var s, l, u, c = n;
                    for (l = t; r > l; l++)for (s = e; i > s; s++)u = 4 * (l * a + s), o[u] = c[o[u]], o[u + 1] = c[o[u + 1]], o[u + 2] = c[o[u + 2]]
                })
            }), o("brightness", function (e) {
                e = Math.floor(255 * e), T(function (t, n, i, r, o, a) {
                    var s, l, u;
                    for (l = n; r > l; l++)for (s = t; i > s; s++)u = 4 * (l * a + s), o[u] += e, o[u + 1] += e, o[u + 2] += e
                })
            }), o("contrast", function (e) {
                for (var t = 256, n, i = []; t--;)n = 255 * ((t / 255 - .5) * e + .5), i[t] = 0 > n ? 0 : n > 255 ? 255 : n;
                T(function (e, t, n, r, o, a) {
                    var s, l, u, c = i;
                    for (l = t; r > l; l++)for (s = e; n > s; s++)u = 4 * (l * a + s), o[u] = c[o[u]], o[u + 1] = c[o[u + 1]], o[u + 2] = c[o[u + 2]]
                })
            }), o("colorize", function (e, t, n) {
                for (var i = 256, r = [], o = [], a = []; i--;)r[i] = i * e, o[i] = i * t, a[i] = i * n;
                T(function (e, t, n, i, s, l) {
                    var u, c, d, f = r, p = o, h = a;
                    for (c = t; i > c; c++)for (u = e; n > u; u++)d = 4 * (c * l + u), s[d] = f[s[d]], s[d + 1] = p[s[d + 1]], s[d + 2] = h[s[d + 2]]
                })
            }), o("exposure", function (e) {
                for (var t = 256, n = []; t--;)n[t] = 255 * (1 - Math.exp(-(t / 255) * e));
                T(function (e, t, i, r, o, a) {
                    var s, l, u, c = n;
                    for (l = t; r > l; l++)for (s = e; i > s; s++)u = 4 * (l * a + s), o[u] = c[o[u]], o[u + 1] = c[o[u + 1]], o[u + 2] = c[o[u + 2]]
                })
            }), o("sepia", function (e) {
                T(function (t, n, i, r, o, a) {
                    var s, l, u, c;
                    for (l = n; r > l; l++)for (s = t; i > s; s++)u = 4 * (l * a + s), c = .3 * o[u] + .59 * o[u + 1] + .11 * o[u + 2], o[u] = c + 40, o[u + 1] = c + 20, o[u + 2] = c - e
                })
            }), o("saturate", function (e) {
                e = -1 * e, T(function (t, n, i, r, o, a) {
                    var s, l, u, c, d, f, p;
                    for (l = n; r > l; l++)for (s = t; i > s; s++)u = 4 * (l * a + s), c = o[u], d = o[u + 1], f = o[u + 2], p = (c + d + f) / 3, o[u] += (p - c) * e, o[u + 1] += (p - d) * e, o[u + 2] += (p - f) * e
                })
            }), o("sharpen", function () {
                A([0, -1, 0, -1, 5, -1, 0, -1, 0])
            }), o("emboss", function () {
                A([-2, -1, 0, -1, 1, 1, 0, 1, 2])
            }), o("blur", function (e) {
                e = e || 1, T(function (t, n, i, r, o, a, s) {
                    var l, u, c, d, f, p, h, m, g, v, x, y;
                    for (c = e; c--;)for (u = n; r > u; u++)for (l = t; i > l; l++) {
                        for (m = f = p = h = 0, v = -1; 2 > v; v++)for (g = -1; 2 > g; g++)x = l + g, y = u + v, x = 0 > x ? 0 : x > a ? a : x, y = 0 > y ? 0 : y > s ? s : y, d = 4 * (y * a + x), f += o[d], p += o[d + 1], h += o[d + 2], m++;
                        d = 4 * (u * a + l), o[d] = f / m, o[d + 1] = p / m, o[d + 2] = h / m
                    }
                })
            }), N(), {loadFromUrl: l, loadFromImage: s, getAsDataUrl: D, getSize: k, undo: y, redo: b, revert: w, canUndo: m, canRedo: g, addUndoLevel: d, setUndoEnabled: c, onChange: S, getCanvas: function () {
                return O
            }, getWebGlCanvas: function () {
                return X
            }, filter: function (e) {
                return H({liveGPU: function () {
                    V[e].apply(this, arguments)
                }, live: function () {
                    V[e].apply(this, arguments)
                }})
            }, resize: h(E), crop: h(R), flip: h(C), rotate: h(_), hasCanvasSupport: function () {
                return!!L
            }, hasWebGlSupport: function () {
                return!!G
            }}
        }
    }), i($, [], function () {
        function e(e) {
            return o.lastIndex = 0, o.test(e) ? '"' + e.replace(o, function (e) {
                var t = l[e];
                return"string" == typeof t ? t : "\\u" + ("0000" + e.charCodeAt(0).toString(16)).slice(-4)
            }) + '"' : '"' + e + '"'
        }

        function n(t, i) {
            var r, o, l, c, d = a, f, p = i[t];
            switch (p && "object" == typeof p && "function" == typeof p.toJSON && (p = p.toJSON(t)), "function" == typeof u && (p = u.call(i, t, p)), typeof p) {
                case"string":
                    return e(p);
                case"number":
                    return isFinite(p) ? p + "" : "null";
                case"boolean":
                case"null":
                    return p + "";
                case"object":
                    if (!p)return"null";
                    if (a += s, f = [], "[object Array]" === Object.prototype.toString.apply(p)) {
                        for (c = p.length, r = 0; c > r; r += 1)f[r] = n(r, p) || "null";
                        return l = 0 === f.length ? "[]" : a ? "[\n" + a + f.join(",\n" + a) + "\n" + d + "]" : "[" + f.join(",") + "]", a = d, l
                    }
                    if (u && "object" == typeof u)for (c = u.length, r = 0; c > r; r += 1)"string" == typeof u[r] && (o = u[r], l = n(o, p), l && f.push(e(o) + (a ? ": " : ":") + l)); else for (o in p)Object.prototype.hasOwnProperty.call(p, o) && (l = n(o, p), l && f.push(e(o) + (a ? ": " : ":") + l));
                    return l = 0 === f.length ? "{}" : a ? "{\n" + a + f.join(",\n" + a) + "\n" + d + "}" : "{" + f.join(",") + "}", a = d, l
            }
        }

        var i = {};
        if ("undefined" != typeof JSON)return JSON;
        var r = /[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g, o = /[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g, a, s, l = {"\b": "\\b", "	": "\\t", "\n": "\\n", "\f": "\\f", "\r": "\\r", '"': '\\"', "\\": "\\\\"}, u;
        return"function" != typeof i.stringify && (i.stringify = function (e, t, i) {
            var r;
            if (a = "", s = "", "number" == typeof i)for (r = 0; i > r; r += 1)s += " "; else"string" == typeof i && (s = i);
            if (u = t, t && "function" != typeof t && ("object" != typeof t || "number" != typeof t.length))throw Error("jsJSON.stringify");
            return n("", {"": e})
        }), "function" != typeof i.parse && (i.parse = function (e, n) {
            function i(e, r) {
                var o, a, s = e[r];
                if (s && "object" == typeof s)for (o in s)Object.prototype.hasOwnProperty.call(s, o) && (a = i(s, o), a !== t ? s[o] = a : delete s[o]);
                return n.call(e, r, s)
            }

            var o;
            if (e += "", r.lastIndex = 0, r.test(e) && (e = e.replace(r, function (e) {
                return"\\u" + ("0000" + e.charCodeAt(0).toString(16)).slice(-4)
            })), /^[\],:{}\s]*$/.test(e.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g, "@").replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g, "]").replace(/(?:^|:|,)(?:\s*\[)+/g, "")))return o = window[String.fromCharCode(101) + "val"]("(" + e + ")"), "function" == typeof n ? i({"": o}, "") : o;
            throw new SyntaxError("jsJSON.parse")
        }), i
    }), i(J, [], function () {
        return{send: function (e) {
            function t() {
            }

            function n() {
                if (!u || 4 == i.readyState || o++ > s) {
                    if (s > o && 200 == i.status)d.call(a, i.responseText); else {
                        var t = o > s ? 1 : 2;
                        f.call(a, {code: t, message: -1 == t ? "Request timed out" : "Unknown error", httpRequest: i, options: e, status: i.status})
                    }
                    i = null
                } else l.setTimeout(n, 10)
            }

            var i, r, o = 0, a = this, s = 1e4, l = window, u, c, d, f;
            if (u = e.async !== !1, c = e.data || "", d = e.success || t, f = e.error || t, c && !e.contentType && (e.contentType = "application/x-www-form-urlencoded; charset=UTF-8"), l.XMLHttpRequest ? i = new XMLHttpRequest : l.ActiveXObject && (i = new ActiveXObject("Microsoft.XMLHTTP")), i) {
                if (i.overrideMimeType && i.overrideMimeType(e.contenType), i.open(e.method || (c ? "POST" : "GET"), e.url, u), e.contentType && i.setRequestHeader("Content-Type", e.contentType), i.setRequestHeader("X-Requested-With", "XMLHttpRequest"), i.send(c), !u)return n();
                r = l.setTimeout(n, 10)
            }
        }}
    }), i(K, [$, J], function (e, n) {
        function i(e) {
            window.console ? console.log("Error", e) : alert("Error: " + e.message + "\n" + e)
        }

        function r(a) {
            a = a || {}, this.exec = function (s, l, u, c) {
                function d(e) {
                    e = {code: -32603, message: "Internal error", data: e}, c(e)
                }

                var f;
                c = c || r.errorHandler || i, f = e.stringify({id: "i" + o++, method: s, params: l || [], jsonrpc: "2.0"}), n.send({url: a.url || r.url, data: "json=" + encodeURIComponent(f), success: function (n) {
                    try {
                        n = e.parse(n)
                    } catch (i) {
                        return d(n), t
                    }
                    n.error ? c(n.error) : u && u(n.result)
                }, error: function (e) {
                    d({statusCode: e.status})
                }})
            }
        }

        var o = 0;
        return r.exec = function (e, t, n, i) {
            return(new r).exec(e, t, n || r.successCallback, i || r.errorCallback)
        }, r
    }), i(Z, [], function () {
        return{normalize: function (e) {
            var t, n, i, r = [];
            for (e = e.split("/"), t = 0, n = e.length; n > t; t++)i = e[t], "." !== i && (0 === t || t == n - 1 || i.length > 0) && (".." === i ? r.length > 1 && r.pop() : r.push(i));
            return 1 === r.length && 0 === r[0].length ? "/" : r.join("/")
        }, join: function (e, t) {
            return e.replace(/\/$/, "") + "/" + t.replace(/^\//, "")
        }, resolve: function (e, t) {
            return/^\//.test(t) ? this.normalize(t) : this.normalize(this.join(e, t))
        }, relative: function (e, t) {
            var n, i, r = [], o;
            if (e = this.normalize(e).split("/"), t = this.normalize(t).split("/"), e.length >= t.length) {
                for (n = 0, i = e.length; i > n; n++)if (n >= t.length || e[n] != t[n]) {
                    o = n;
                    break
                }
            } else {
                for (n = 0, i = t.length; i > n; n++)if (n >= e.length || e[n] != t[n]) {
                    o = n;
                    break
                }
                o > e.length - 1 && (e = [])
            }
            if (0 === o)return r.join("/");
            for (n = 0, i = e.length - o; i > n; n++)r.push("..");
            for (n = o, i = t.length; i > n; n++)r.push(t[n]);
            return e.length > 0 && r.length > 0 && (0 === e[e.length - 1].length && e.pop(), 0 === r[0].length && r.shift()), e.concat(r).join("/")
        }, dirname: function (e) {
            return e.replace(/\\/g, "/").replace(/\/[^\/]*\/?$/, "")
        }, basename: function (e, t) {
            return e = e.replace(/\/$/, "").replace(/^.*[\/\\]/g, ""), t && e.substr(e.length - t.length) == t && (e = e.substr(0, e.length - t.length)), e
        }, extname: function (e) {
            var t = e.lastIndexOf(".");
            return-1 != t ? e.substr(t) : ""
        }}
    }), i(Q, [], function () {
        return{ie7: document.all && !window.opera && !document.documentMode}
    }), i(et, [], function () {
        var e, t = function (t) {
            return t === e ? "undefined" : null === t ? "null" : t.nodeType ? "node" : {}.toString.call(t).match(/\s([a-z|A-Z]+)/)[1].toLowerCase()
        }, n = function (r) {
            return i(arguments, function (o, s) {
                s > 0 && i(o, function (i, o) {
                    i !== e && (t(r[o]) === t(i) && ~a(t(i), ["array", "object"]) ? n(r[o], i) : r[o] = i)
                })
            }), r
        }, i = function (t, n) {
            var i, r, o;
            if (t) {
                try {
                    i = t.length
                } catch (a) {
                    i = e
                }
                if (i === e) {
                    for (r in t)if (t.hasOwnProperty(r) && n(t[r], r) === !1)return
                } else for (o = 0; i > o; o++)if (n(t[o], o) === !1)return
            }
        }, r = function (e) {
            var n;
            if (!e || "object" !== t(e))return!0;
            for (n in e)return!1;
            return!0
        }, o = function (e, n) {
            function i(r) {
                "function" === t(e[r]) && e[r](function (e) {
                    o > ++r && !e ? i(r) : n(e)
                })
            }

            var r = 0, o = e.length;
            "function" !== t(n) && (n = function () {
            }), e && e.length || n(), i(r)
        }, a = function (e, t) {
            if (t) {
                if (Array.prototype.indexOf)return Array.prototype.indexOf.call(t, e);
                for (var n = 0, i = t.length; i > n; n++)if (t[n] === e)return n
            }
            return-1
        }, s = function (e, n) {
            var i = [];
            "array" !== t(e) && (e = [e]), "array" !== t(n) && (n = [n]);
            for (var r in e)-1 === a(e[r], n) && i.push(e[r]);
            return i.length ? i : !1
        }, l = function (e) {
            var t, n = [];
            for (t = 0; e.length > t; t++)n[t] = e[t];
            return n
        }, u = function () {
            var e = 0;
            return function (t) {
                var n = (new Date).getTime().toString(32), i;
                for (i = 0; 5 > i; i++)n += Math.floor(65535 * Math.random()).toString(32);
                return(t || "o_") + n + (e++).toString(32)
            }
        }(), c = function (e) {
            return e ? String.prototype.trim ? String.prototype.trim.call(e) : ("" + e).replace(/^\s*/, "").replace(/\s*$/, "") : e
        }, d = function (e) {
            if ("string" != typeof e)return e;
            var t = {t: 1099511627776, g: 1073741824, m: 1048576, k: 1024}, n;
            return e = /^([0-9]+)([mgk]?)$/.exec(e.toLowerCase().replace(/[^0-9mkg]/g, "")), n = e[2], e = +e[1], t.hasOwnProperty(n) && (e *= t[n]), e
        };
        return{guid: u, typeOf: t, extend: n, each: i, isEmptyObj: r, inSeries: o, inArray: a, arrayDiff: s, toArray: l, trim: c, parseSizeStr: d}
    }), i(tt, [et], function (e) {
        function t(e, t) {
            var n;
            for (n in e)if (e[n] === t)return n;
            return null
        }

        return{RuntimeError: function () {
            function n(e) {
                this.code = e, this.name = t(i, e), this.message = this.name + ": RuntimeError " + this.code
            }

            var i = {NOT_INIT_ERR: 1, NOT_SUPPORTED_ERR: 9, JS_ERR: 4};
            return e.extend(n, i), n.prototype = Error.prototype, n
        }(), OperationNotAllowedException: function () {
            function t(e) {
                this.code = e, this.name = "OperationNotAllowedException"
            }

            return e.extend(t, {NOT_ALLOWED_ERR: 1}), t.prototype = Error.prototype, t
        }(), ImageError: function () {
            function n(e) {
                this.code = e, this.name = t(i, e), this.message = this.name + ": ImageError " + this.code
            }

            var i = {WRONG_FORMAT: 1};
            return e.extend(n, i), n.prototype = Error.prototype, n
        }(), FileException: function () {
            function n(e) {
                this.code = e, this.name = t(i, e), this.message = this.name + ": FileException " + this.code
            }

            var i = {NOT_FOUND_ERR: 1, SECURITY_ERR: 2, ABORT_ERR: 3, NOT_READABLE_ERR: 4, ENCODING_ERR: 5, NO_MODIFICATION_ALLOWED_ERR: 6, INVALID_STATE_ERR: 7, SYNTAX_ERR: 8};
            return e.extend(n, i), n.prototype = Error.prototype, n
        }(), DOMException: function () {
            function n(e) {
                this.code = e, this.name = t(i, e), this.message = this.name + ": DOMException " + this.code
            }

            var i = {INDEX_SIZE_ERR: 1, DOMSTRING_SIZE_ERR: 2, HIERARCHY_REQUEST_ERR: 3, WRONG_DOCUMENT_ERR: 4, INVALID_CHARACTER_ERR: 5, NO_DATA_ALLOWED_ERR: 6, NO_MODIFICATION_ALLOWED_ERR: 7, NOT_FOUND_ERR: 8, NOT_SUPPORTED_ERR: 9, INUSE_ATTRIBUTE_ERR: 10, INVALID_STATE_ERR: 11, SYNTAX_ERR: 12, INVALID_MODIFICATION_ERR: 13, NAMESPACE_ERR: 14, INVALID_ACCESS_ERR: 15, VALIDATION_ERR: 16, TYPE_MISMATCH_ERR: 17, SECURITY_ERR: 18, NETWORK_ERR: 19, ABORT_ERR: 20, URL_MISMATCH_ERR: 21, QUOTA_EXCEEDED_ERR: 22, TIMEOUT_ERR: 23, INVALID_NODE_TYPE_ERR: 24, DATA_CLONE_ERR: 25};
            return e.extend(n, i), n.prototype = Error.prototype, n
        }(), EventException: function () {
            function t(e) {
                this.code = e, this.name = "EventException"
            }

            return e.extend(t, {UNSPECIFIED_EVENT_TYPE_ERR: 0}), t.prototype = Error.prototype, t
        }()}
    }), i(nt, [tt, et], function (e, n) {
        function i() {
            var i = {};
            n.extend(this, {uid: null, init: function () {
                this.uid || (this.uid = n.guid("uid_"))
            }, addEventListener: function (e, r, o, a) {
                var s = this, l;
                return e = n.trim(e), /\s/.test(e) ? (n.each(e.split(/\s+/), function (e) {
                    s.addEventListener(e, r, o, a)
                }), t) : (e = e.toLowerCase(), o = parseInt(o, 10) || 0, l = i[this.uid] && i[this.uid][e] || [], l.push({fn: r, priority: o, scope: a || this}), i[this.uid] || (i[this.uid] = {}), i[this.uid][e] = l, t)
            }, hasEventListener: function (e) {
                return e ? !(!i[this.uid] || !i[this.uid][e]) : !!i[this.uid]
            }, removeEventListener: function (e, t) {
                e = e.toLowerCase();
                var r = i[this.uid] && i[this.uid][e], o;
                if (r) {
                    if (t) {
                        for (o = r.length - 1; o >= 0; o--)if (r[o].fn === t) {
                            r.splice(o, 1);
                            break
                        }
                    } else r = [];
                    r.length || (delete i[this.uid][e], n.isEmptyObj(i[this.uid]) && delete i[this.uid])
                }
            }, removeAllEventListeners: function () {
                i[this.uid] && delete i[this.uid]
            }, dispatchEvent: function (t) {
                var r, o, a, s, l = {};
                if ("string" !== n.typeOf(t)) {
                    if (s = t, "string" !== n.typeOf(s.type))throw new e.EventException(e.EventException.UNSPECIFIED_EVENT_TYPE_ERR);
                    t = s.type, s.total && s.loaded && (l.total = s.total, l.loaded = s.loaded)
                }
                if (-1 !== t.indexOf("::") ? function (e) {
                    r = e[0], t = e[1]
                }(t.split("::")) : r = this.uid, t = t.toLowerCase(), o = i[r] && i[r][t]) {
                    o.sort(function (e, t) {
                        return t.priority - e.priority
                    }), a = [].slice.call(arguments), a.shift(), l.type = t, a.unshift(l);
                    var u = [];
                    n.each(o, function (e) {
                        a[0].target = e.scope, l.async ? u.push(function (t) {
                            setTimeout(function () {
                                t(e.fn.apply(e.scope, a) === !1)
                            }, 1)
                        }) : u.push(function (t) {
                            t(e.fn.apply(e.scope, a) === !1)
                        })
                    }), u.length && n.inSeries(u)
                }
                return!0
            }, bind: function () {
                this.addEventListener.apply(this, arguments)
            }, unbind: function () {
                this.removeEventListener.apply(this, arguments)
            }, unbindAll: function () {
                this.removeAllEventListeners.apply(this, arguments)
            }, trigger: function () {
                this.dispatchEvent.apply(this, arguments)
            }, convertEventPropsToHandlers: function (e) {
                var i;
                "array" !== n.typeOf(e) && (e = [e]);
                for (var r = 0; e.length > r; r++)i = "on" + e[r], "function" === n.typeOf(this[i]) ? this.addEventListener(e[r], this[i]) : this[i] === t && (this[i] = null)
            }})
        }

        return i.instance = new i, i
    }), i(it, [], function () {
        var e = function (e) {
            return unescape(encodeURIComponent(e))
        }, t = function (e) {
            return decodeURIComponent(escape(e))
        }, n = function (e) {
            if ("function" == typeof window.atob)return window.atob(e);
            var t = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=", n, i, r, o, a, s, l, u, c = 0, d = 0, f = "", p = [];
            if (!e)return e;
            e += "";
            do o = t.indexOf(e.charAt(c++)), a = t.indexOf(e.charAt(c++)), s = t.indexOf(e.charAt(c++)), l = t.indexOf(e.charAt(c++)), u = o << 18 | a << 12 | s << 6 | l, n = 255 & u >> 16, i = 255 & u >> 8, r = 255 & u, p[d++] = 64 == s ? String.fromCharCode(n) : 64 == l ? String.fromCharCode(n, i) : String.fromCharCode(n, i, r); while (e.length > c);
            return f = p.join("")
        }, i = function (e) {
            if ("function" == typeof window.btoa)return window.btoa(e);
            var t = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=", n, i, r, o, a, s, l, u, c = 0, d = 0, f = "", p = [];
            if (!e)return e;
            do n = e.charCodeAt(c++), i = e.charCodeAt(c++), r = e.charCodeAt(c++), u = n << 16 | i << 8 | r, o = 63 & u >> 18, a = 63 & u >> 12, s = 63 & u >> 6, l = 63 & u, p[d++] = t.charAt(o) + t.charAt(a) + t.charAt(s) + t.charAt(l); while (e.length > c);
            f = p.join("");
            var h = e.length % 3;
            return(h ? f.slice(0, h - 3) : f) + "===".slice(h || 3)
        };
        return{utf8_encode: e, utf8_decode: t, atob: n, btoa: i}
    }), i(rt, [], function () {
        var e = function (e) {
            for (var t = ["source", "scheme", "authority", "userInfo", "user", "pass", "host", "port", "relative", "path", "directory", "file", "query", "fragment"], n = t.length, i = {http: 80, https: 443}, r = {}, o = /^(?:([^:\/?#]+):)?(?:\/\/()(?:(?:()(?:([^:@]*):?([^:@]*))?@)?([^:\/?#]*)(?::(\d*))?))?()(?:(()(?:(?:[^?#\/]*\/)*)()(?:[^?#]*))(?:\\?([^#]*))?(?:#(.*))?)/, a = o.exec(e || ""); n--;)a[n] && (r[t[n]] = a[n]);
            if (/^[^\/]/.test(r.path) && !r.scheme) {
                var s = document.location.pathname;
                /(\/|\/[^\.]+)$/.test(s) || (s = s.replace(/[^\/]+$/, "")), r.host = document.location.hostname, r.path = s + (r.path || "")
            }
            return r.scheme || (r.scheme = document.location.protocol.replace(/:$/, "")), r.host || (r.host = document.location.hostname), r.port || (r.port = document.location.port || i[r.scheme] || 80), r.port = parseInt(r.port, 10), r.path || (r.path = "/"), delete r.source, r
        }, t = function (t) {
            var n = {http: 80, https: 443}, i = e(t);
            return i.scheme + "://" + i.host + (i.port !== n[i.scheme] ? ":" + i.port : "") + i.path + (i.query ? i.query : "")
        }, n = function (t) {
            function n(e) {
                return[e.scheme, e.host, e.port].join("/")
            }

            return"string" == typeof t && (t = e(t)), n(e()) === n(t)
        };
        return{parseUrl: e, resolveUrl: t, hasSameOrigin: n}
    }), i(ot, [et], function (e) {
        function t(e) {
            for (var t, n, i = 0; e.length > i; i++)if (t = e[i].s1, n = e[i].prop, o = e[i].sv || e[i].id, t) {
                if (-1 != t.indexOf(e[i].s2))return e[i].id
            } else if (n)return e[i].id
        }

        function n(e) {
            var t = e.indexOf(o);
            if (-1 != t)return parseFloat(e.substring(t + o.length + 1))
        }

        var i = [
            {s1: navigator.userAgent, s2: "Android", id: "Android Browser", sv: "Version"},
            {s1: navigator.userAgent, s2: "Chrome", id: "Chrome"},
            {s1: navigator.vendor, s2: "Apple", id: "Safari", sv: "Version"},
            {prop: window.opera && window.opera.buildNumber, id: "Opera", sv: "Version"},
            {s1: navigator.vendor, s2: "KDE", id: "Konqueror"},
            {s1: navigator.userAgent, s2: "Firefox", id: "Firefox"},
            {s1: navigator.vendor, s2: "Camino", id: "Camino"},
            {s1: navigator.userAgent, s2: "Netscape", id: "Netscape"},
            {s1: navigator.userAgent, s2: "MSIE", id: "IE", sv: "MSIE"},
            {s1: navigator.userAgent, s2: "Gecko", id: "Mozilla", sv: "rv"}
        ], r = [
            {s1: navigator.platform, s2: "Win", id: "Windows"},
            {s1: navigator.platform, s2: "Mac", id: "Mac"},
            {s1: navigator.userAgent, s2: "iPhone", id: "iOS"},
            {s1: navigator.userAgent, s2: "iPad", id: "iOS"},
            {s1: navigator.userAgent, s2: "Android", id: "Android"},
            {s1: navigator.platform, s2: "Linux", id: "Linux"}
        ], o, a = function () {
            var t = {define_property: function () {
                return!1
            }(), create_canvas: function () {
                var e = document.createElement("canvas");
                return!(!e.getContext || !e.getContext("2d"))
            }(), return_response_type: function (t) {
                if (!window.XMLHttpRequest)return!1;
                try {
                    var n = new XMLHttpRequest;
                    if ("undefined" !== e.typeOf(n.responseType))return n.open("get", "infinity-8.me"), n.responseType = t, n.responseType !== t ? !1 : !0
                } catch (i) {
                }
                return!1
            }, use_data_uri: function () {
                var e = new Image;
                return e.onload = function () {
                    t.use_data_uri = 1 === e.width && 1 === e.height
                }, setTimeout(function () {
                    e.src = "data:image/gif;base64,R0lGODlhAQABAIAAAP8AAAAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw=="
                }, 1), !1
            }(), use_data_uri_over32kb: function () {
                return t.use_data_uri && ("IE" !== s.browser || s.version >= 9)
            }, use_data_uri_of: function (e) {
                return t.use_data_uri && 33e3 > e || t.use_data_uri_over32kb()
            }, use_fileinput: function () {
                var e = document.createElement("input");
                return e.setAttribute("type", "file"), !e.disabled
            }};
            return function (n) {
                var i = [].slice.call(arguments);
                return i.shift(), "function" === e.typeOf(t[n]) ? t[n].apply(this, i) : !!t[n]
            }
        }(), s = {can: a, browser: t(i), version: n(navigator.userAgent) || n(navigator.appVersion), OS: t(r), swf_url: "../flash/Moxie.swf", xap_url: "../silverlight/Moxie.xap", global_event_dispatcher: "moxie.core.EventTarget.instance.dispatchEvent"};
        return s
    }), i(at, [ot], function (e) {
        var n = function (e) {
            return"string" != typeof e ? e : document.getElementById(e)
        }, i = function (e, t) {
            var n;
            return"" === e.className ? !1 : (n = RegExp("(^|\\s+)" + t + "(\\s+|$)"), n.test(e.className))
        }, r = function (e, t) {
            i(e, t) || (e.className = "" === e.className ? t : e.className.replace(/\s+$/, "") + " " + t)
        }, o = function (e, t) {
            var n = RegExp("(^|\\s+)" + t + "(\\s+|$)");
            e.className = e.className.replace(n, function (e, t, n) {
                return" " === t && " " === n ? " " : ""
            })
        }, a = function (e, n) {
            return e.currentStyle ? e.currentStyle[n] : window.getComputedStyle ? window.getComputedStyle(e, null)[n] : t
        }, s = function (t, n) {
            function i(e) {
                var t, n, i = 0, r = 0;
                return e && (n = e.getBoundingClientRect(), t = "CSS1Compat" === s.compatMode ? s.documentElement : s.body, i = n.left + t.scrollLeft, r = n.top + t.scrollTop), {x: i, y: r}
            }

            var r = 0, o = 0, a, s = document, l, u;
            if (t = t, n = n || s.body, t && t.getBoundingClientRect && "IE" === e.browser && (!s.documentMode || 8 > s.documentMode))return l = i(t), u = i(n), {x: l.x - u.x, y: l.y - u.y};
            for (a = t; a && a != n && a.nodeType;)r += a.offsetLeft || 0, o += a.offsetTop || 0, a = a.offsetParent;
            for (a = t.parentNode; a && a != n && a.nodeType;)r -= a.scrollLeft || 0, o -= a.scrollTop || 0, a = a.parentNode;
            return{x: r, y: o}
        }, l = function (e) {
            return{w: e.offsetWidth || e.clientWidth, h: e.offsetHeight || e.clientHeight}
        };
        return{get: n, hasClass: i, addClass: r, removeClass: o, getStyle: a, getPos: s, getSize: l}
    }), i(st, [et, at, nt], function (e, t, n) {
        function i(n, i, r) {
            var a = this, s = e.guid(n + "_");
            o[s] = this, r = e.extend({access_binary: !1, access_image_binary: !1, display_media: !1, drag_and_drop: !1, resize_image: !1, report_upload_progress: !1, return_response_headers: !0, return_response_type: !1, return_status_code: !0, send_custom_headers: !1, select_folder: !1, select_multiple: !0, send_binary_string: !1, send_browser_cookies: !0, send_multipart: !0, slice_blob: !1, stream_upload: !1, summon_file_dialog: !1, upload_filesize: !0, use_http_method: !0}, r), e.extend(this, {initialized: !1, uid: s, type: n, shimid: s + "_container", clients: 0, options: i, can: function (t, n) {
                if ("string" === e.typeOf(t) && "undefined" === e.typeOf(n) && (t = function (t) {
                    var n = {};
                    return e.each(t, function (e) {
                        n[e] = !0
                    }), n
                }(t.split(","))), "object" === e.typeOf(t)) {
                    for (var i in t)if (!this.can(i, t[i]))return!1;
                    return!0
                }
                return"function" === e.typeOf(r[t]) ? r[t].call(this, n) : r[t] || !1
            }, getShimContainer: function () {
                var n, i = t.get(this.shimid);
                return i || (n = this.options.container ? t.get(this.options.container) : document.body, i = document.createElement("div"), i.id = this.shimid, i.className = "moxie-shim moxie-shim-" + this.type, e.extend(i.style, {position: "absolute", top: "0px", left: "0px", width: "1px", height: "1px", overflow: "hidden"}), n.appendChild(i), n = null), i
            }, getShim: function () {
                return t.get(this.uid)
            }, exec: function (e, t) {
                var n = [].slice.call(arguments, 2);
                return a[e] && a[e][t] ? a[e][t].apply(this, n) : a.shimExec.apply(this, arguments)
            }, shimExec: function (e, t) {
                var n = [].slice.call(arguments, 2);
                return a.getShim().exec(this.uid, e, t, n)
            }, destroy: function () {
                var e = this.getShimContainer();
                e && (e.parentNode.removeChild(e), e = null), this.unbindAll(), delete o[this.uid], s = a = null
            }})
        }

        var r = {}, o = {};
        return i.order = "html5,flash,silverlight,html4", i.getRuntime = function (e) {
            return o[e] ? o[e] : !1
        }, i.addConstructor = function (e, t) {
            t.prototype = n.instance, r[e] = t
        }, i.getConstructor = function (e) {
            return r[e] || null
        }, i.getInfo = function (e) {
            var t = i.getRuntime(e);
            return t ? {uid: t.uid, type: t.type, can: t.can} : null
        }, i
    }), i(lt, [tt, et, st], function (e, n, i) {
        return function r() {
            var r;
            n.extend(this, {connectRuntime: function (o) {
                function a(n) {
                    var l, u;
                    return n.length ? (l = n.shift(), (u = i.getConstructor(l)) ? (r = new u(o), o.required_caps && !r.can(o.required_caps) ? (a(n), t) : (r.bind("Init", function () {
                        r.initialized = !0, setTimeout(function () {
                            r.clients++, s.trigger("RuntimeInit", r)
                        }, 1)
                    }), r.bind("Error", function (e, t) {
                        r.destroy(), a(n)
                    }), r.init(), t)) : (a(n), t)) : (s.trigger("RuntimeError", new e.RuntimeError(e.RuntimeError.NOT_INIT_ERR)), r = null, t)
                }

                var s = this, l;
                if ("string" === n.typeOf(o) ? l = o : "string" === n.typeOf(o.ruid) && (l = o.ruid), l) {
                    if (r = i.getRuntime(l))return r.clients++, r;
                    throw new e.RuntimeError(e.RuntimeError.NOT_INIT_ERR)
                }
                a((o.runtime_order || i.order).split(/\s*,\s*/))
            }, getRuntime: function () {
                return r || null
            }, disconnectRuntime: function () {
                r && 0 >= --r.clients && (r.destroy(), r = null)
            }})
        }
    }), i(ut, [et, lt, nt], function (e, t, n) {
        function i() {
            this.uid = e.guid("uid_"), t.call(this), this.destroy = function () {
                this.disconnectRuntime(), this.unbindAll()
            }
        }

        return i.prototype = n.instance, i
    }), i(ct, [et, it, lt], function (e, t, n) {
        function i(o, a) {
            function s(t, n, o) {
                var a, s = r[this.uid];
                return"string" === e.typeOf(s) && s.length ? (a = new i(null, {type: o, size: n - t}), a.detach(s.substr(t, a.size)), a) : null
            }

            function l() {
                return"function" !== e.typeOf(this.connectRuntime) && n.call(this), this.connectRuntime(this.ruid)
            }

            a ? "string" === e.typeOf(a) && (a = {data: a}) : a = {}, e.extend(this, {uid: a.uid || e.guid("uid_"), ruid: o, size: a.size || 0, type: a.type || "", slice: function (e, t, n) {
                return this.isDetached() ? s.apply(this, arguments) : l.call(this).exec.call(this, "Blob", "slice", this.getSource(), e, t, n)
            }, getSource: function () {
                return r[this.uid] ? r[this.uid] : null
            }, detach: function (e) {
                this.ruid && (l.call(this).exec.call(this, "Blob", "destroy", r[this.uid]), this.disconnectRuntime(), this.ruid = null), e = e || "";
                var n = e.match(/^data:([^;]*);base64,/);
                n && (this.type = n[1], e = t.atob(e.substring(e.indexOf("base64,") + 7))), this.size = e.length, r[this.uid] = e
            }, isDetached: function () {
                return!this.ruid && "string" === e.typeOf(r[this.uid])
            }, destroy: function () {
                this.detach(), delete r[this.uid]
            }}), a.data ? this.detach(a.data) : r[this.uid] = a
        }

        var r = {};
        return i
    }), i(dt, [tt, et, ct], function (e, t, n) {
        function i() {
            var e, i = {}, r = "";
            t.extend(this, {append: function (r, o) {
                var a = this, s = t.typeOf(o);
                o instanceof n ? (e && delete i[e], e = r, i[r] = [o]) : "array" === s ? (r += "[]", t.each(o, function (e) {
                    a.append.call(a, r, e)
                })) : "object" === s ? t.each(o, function (e, t) {
                    a.append.call(a, r + "[" + t + "]", e)
                }) : (o = "" + o, i[r] || (i[r] = []), i[r].push(o))
            }, hasBlob: function () {
                return!!e
            }, getBlob: function () {
                return i[e] && i[e][0] || null
            }, getBlobName: function () {
                return e || null
            }, each: function (e) {
                var n = this;
                t.each(i, function (n, i) {
                    t.each(n, function (t) {
                        e(t, i)
                    })
                })
            }, destroy: function () {
                e = null, r = "", i = {}
            }})
        }

        return i
    }), i(ft, [et], function (e) {
        var t = {};
        return{addI18n: function (n) {
            return e.extend(t, n)
        }, translate: function (e) {
            return t[e] || e
        }, _: function (e) {
            return this.translate(e)
        }}
    }), i(pt, [et, ft], function (e, t) {
        var n = "application/msword,doc dot,application/pdf,pdf,application/pgp-signature,pgp,application/postscript,ps ai eps,application/rtf,rtf,application/vnd.ms-excel,xls xlb,application/vnd.ms-powerpoint,ppt pps pot,application/zip,zip,application/x-shockwave-flash,swf swfl,application/vnd.openxmlformats-officedocument.wordprocessingml.document,docx,application/vnd.openxmlformats-officedocument.wordprocessingml.template,dotx,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,xlsx,application/vnd.openxmlformats-officedocument.presentationml.presentation,pptx,application/vnd.openxmlformats-officedocument.presentationml.template,potx,application/vnd.openxmlformats-officedocument.presentationml.slideshow,ppsx,application/x-javascript,js,application/json,json,audio/mpeg,mp3 mpga mpega mp2,audio/x-wav,wav,audio/mp4,m4a,image/bmp,bmp,image/gif,gif,image/jpeg,jpg jpeg jpe,image/photoshop,psd,image/png,png,image/svg+xml,svg svgz,image/tiff,tiff tif,text/plain,asc txt text diff log,text/html,htm html xhtml,text/css,css,text/csv,csv,text/rtf,rtf,video/mpeg,mpeg mpg mpe m2v,video/quicktime,qt mov,video/mp4,mp4,video/x-m4v,m4v,video/x-flv,flv,video/x-ms-wmv,wmv,video/avi,avi,video/webm,webm,video/3gpp,3gp,video/3gpp2,3g2,video/vnd.rn-realvideo,rv,application/vnd.oasis.opendocument.formula-template,otf,application/octet-stream,exe", i = {mimes: {}, extensions: {}, addMimeType: function (e) {
            var t = e.split(/,/), n, i, r;
            for (n = 0; t.length > n; n += 2) {
                for (r = t[n + 1].split(/ /), i = 0; r.length > i; i++)this.mimes[r[i]] = t[n];
                this.extensions[t[n]] = r
            }
        }, extList2mimes: function (t) {
            var n = this, i, r, o, a, s = [];
            e:for (r = 0; t.length > r; r++)for (i = t[r].extensions.split(/\s*,\s*/), o = 0; i.length > o; o++) {
                if ("*" === i[o]) {
                    s = [];
                    break e
                }
                a = n.mimes[i[o]], a && !~e.inArray(a, s) && s.push(a)
            }
            return s
        }, mimes2extList: function (n) {
            var i = this, r = "", o = [];
            return n = e.trim(n), "*" !== n ? e.each(n.split(/\s*,\s*/), function (e) {
                i.extensions[e] && (r += i.extensions[e].join(","))
            }) : r = n, o.push({title: t.translate("Files"), extensions: r}), o.mimes = n, o
        }, getFileExtension: function (e) {
            var t = e && e.match(/\.([^.]+)$/);
            return t ? t[1].toLowerCase() : ""
        }, getFileMime: function (e) {
            return this.mimes[this.getFileExtension(e)] || ""
        }};
        return i.addMimeType(n), i
    }), i(ht, [et, tt, nt, it, rt, ut, ct, dt, ot, pt], function (e, n, i, r, o, a, s, l, u, c) {
        function d() {
            this.uid = e.guid("uid_")
        }

        function f() {
            function i(e, n) {
                return T.hasOwnProperty(e) ? 1 === arguments.length ? u.can("define_property") ? T[e] : R[e] : (u.can("define_property") ? T[e] = n : R[e] = n, t) : t
            }

            function x() {
                return!window.XMLHttpRequest || "IE" === u.browser && 8 > u.version ? function () {
                    for (var e = ["Msxml2.XMLHTTP.6.0", "Microsoft.XMLHTTP"], t = 0; e.length > t; t++)try {
                        return new ActiveXObject(e[t])
                    } catch (n) {
                    }
                }() : new window.XMLHttpRequest
            }

            function y(e) {
                var t = e.responseXML, n = e.responseText;
                return"IE" === u.browser && n && t && !t.documentElement && /[^\/]+\/[^\+]+\+xml/.test(e.getResponseHeader("Content-Type")) && (t = new window.ActiveXObject("Microsoft.XMLDOM"), t.async = !1, t.validateOnParse = !1, t.loadXML(n)), t && ("IE" === u.browser && 0 !== t.parseError || !t.documentElement || "parsererror" === t.documentElement.tagName) ? null : t
            }

            function b() {
                var t = this, n = 0;
                G = g, X = x(), X.onreadystatechange = function r() {
                    switch (i("readyState") > f.HEADERS_RECEIVED && (i("status", X.status), i("statusText", X.statusText)), i("readyState", X.readyState), t.dispatchEvent("readystatechange"), i("readyState")) {
                        case f.OPENED:
                            r.loadstartDispatched === p && (t.dispatchEvent("loadstart"), r.loadstartDispatched = !0);
                            break;
                        case f.HEADERS_RECEIVED:
                            try {
                                n = X.getResponseHeader("Content-Length") || 0
                            } catch (e) {
                            }
                            break;
                        case f.LOADING:
                            var o = 0;
                            try {
                                X.responseText && (o = X.responseText.length)
                            } catch (e) {
                                o = 0
                            }
                            t.dispatchEvent({type: "progress", lengthComputable: !!n, total: parseInt(n, 10), loaded: o});
                            break;
                        case f.DONE:
                            X.onreadystatechange = function () {
                            }, 0 === X.status ? (W = !0, t.dispatchEvent("error")) : (i("responseText", X.responseText), i("responseXML", y(X)), i("response", "document" === i("responseType") ? i("responseXML") : i("responseText")), t.dispatchEvent("load")), X = null, t.dispatchEvent("loadend")
                    }
                }, X.open(S, k, A, F, I), e.isEmptyObj(D) || e.each(D, function (e, t) {
                    X.setRequestHeader(t, e)
                }), X.send()
            }

            function w(t) {
                function n() {
                    X.destroy(), X = null, o.dispatchEvent("loadend"), o = null
                }

                function r(r) {
                    X.bind("LoadStart", function (e) {
                        i("readyState", f.LOADING), o.dispatchEvent(e), O && o.upload.dispatchEvent(e)
                    }), X.bind("Progress", function (e) {
                        i("readyState", f.LOADING), o.dispatchEvent(e)
                    }), X.bind("UploadProgress", function (e) {
                        O && o.upload.dispatchEvent({type: "progress", lengthComputable: !1, total: e.total, loaded: e.loaded})
                    }), X.bind("Load", function (t) {
                        i("readyState", f.DONE), i("status", Number(r.exec.call(X, "XMLHttpRequest", "getStatus") || 0)), i("statusText", h[i("status")] || ""), i("response", r.exec.call(X, "XMLHttpRequest", "getResponse", i("responseType"))), ~e.inArray(i("responseType"), ["text", ""]) ? i("responseText", i("response")) : "document" === i("responseType") && i("responseXML", i("response")), i("status") > 0 ? (O && o.upload.dispatchEvent(t), o.dispatchEvent(t)) : (W = !0, o.dispatchEvent("error")), n()
                    }), X.bind("Abort", function (e) {
                        o.dispatchEvent(e), n()
                    }), X.bind("Error", function (e) {
                        W = !0, i("readyState", f.DONE), o.dispatchEvent("readystatechange"), L = !0, o.dispatchEvent(e), n()
                    }), r.exec.call(X, "XMLHttpRequest", "send", {url: k, method: S, async: A, user: F, password: I, headers: D, mimeType: N, encoding: M, responseType: o.responseType, options: q}, t)
                }

                var o = this;
                G = v, X = new a, q.required_caps = e.extend({}, q.required_caps, {return_response_type: o.responseType}), q.ruid ? r(X.connectRuntime(q)) : (X.bind("RuntimeInit", function (e, t) {
                    r(t)
                }), X.bind("RuntimeError", function (e, t) {
                    o.dispatchEvent("RuntimeError", t)
                }), X.connectRuntime(q))
            }

            function _(e) {
                B = (new Date).getTime(), E.call(this) ? b.call(this, e) : w.call(this, e)
            }

            function E() {
                return"HEAD" === S || "GET" === S && !!~e.inArray(i("responseType"), ["", "text", "document"]) || "POST" === S && "application/x-www-form-urlencoded" === D["Content-Type"]
            }

            function C() {
                i("responseText", ""), i("responseXML", null), i("response", null), i("status", 0), i("statusText", ""), B = p, U = p
            }

            var R = this, T = {timeout: 0, readyState: f.UNSENT, withCredentials: !1, status: 0, statusText: "", responseType: "", responseXML: null, responseText: null, response: null}, A = !0, k, S, D = {}, F, I, M = null, N = null, H = !1, P = !1, O = !1, L = !1, W = !1, z = !1, B, U, j = null, V = null, q = {}, X, G = g;
            e.extend(this, T, {uid: e.guid("uid_"), upload: new d, open: function (t, a, s, l, u) {
                var c;
                if (!t || !a)throw new n.DOMException(n.DOMException.SYNTAX_ERR);
                if (/[\u0100-\uffff]/.test(t) || r.utf8_encode(t) !== t)throw new n.DOMException(n.DOMException.SYNTAX_ERR);
                if (~e.inArray(t.toUpperCase(), ["CONNECT", "DELETE", "GET", "HEAD", "OPTIONS", "POST", "PUT", "TRACE", "TRACK"]) && (S = t.toUpperCase()), ~e.inArray(S, ["CONNECT", "TRACE", "TRACK"]))throw new n.DOMException(n.DOMException.SECURITY_ERR);
                if (a = r.utf8_encode(a), c = o.parseUrl(a), z = o.hasSameOrigin(c), k = o.resolveUrl(a), (l || u) && !z)throw new n.DOMException(n.DOMException.INVALID_ACCESS_ERR);
                if (F = l || c.user, I = u || c.pass, A = s || !0, A === !1 && (i("timeout") || i("withCredentials") || "" !== i("responseType")))throw new n.DOMException(n.DOMException.INVALID_ACCESS_ERR);
                H = !A, P = !1, D = {}, C.call(this), i("readyState", f.OPENED), this.convertEventPropsToHandlers(["readystatechange"]), this.dispatchEvent("readystatechange")
            }, setRequestHeader: function (t, o) {
                var a = ["accept-charset", "accept-encoding", "access-control-request-headers", "access-control-request-method", "connection", "content-length", "cookie", "cookie2", "content-transfer-encoding", "date", "expect", "host", "keep-alive", "origin", "referer", "te", "trailer", "transfer-encoding", "upgrade", "user-agent", "via"];
                if (i("readyState") !== f.OPENED || P)throw new n.DOMException(n.DOMException.INVALID_STATE_ERR);
                if (/[\u0100-\uffff]/.test(t) || r.utf8_encode(t) !== t)throw new n.DOMException(n.DOMException.SYNTAX_ERR);
                return t = e.trim(t).toLowerCase(), ~e.inArray(t, a) || /^(proxy\-|sec\-)/.test(t) ? !1 : (D[t] ? D[t] += ", " + o : D[t] = o, !0)
            }, overrideMimeType: function (t) {
                var r, o;
                if (~e.inArray(i("readyState"), [f.LOADING, f.DONE]))throw new n.DOMException(n.DOMException.INVALID_STATE_ERR);
                if (t = e.trim(t.toLowerCase()), /;/.test(t) && (r = t.match(/^([^;]+)(?:;\scharset\=)?(.*)$/)) && (t = r[1], r[2] && (o = r[2])), !c.mimes[t])throw new n.DOMException(n.DOMException.SYNTAX_ERR);
                j = t, V = o
            }, send: function (t, i) {
                var o = this;
                if (q = "string" === e.typeOf(i) ? {ruid: i} : i ? i : {}, this.convertEventPropsToHandlers(m), this.upload.convertEventPropsToHandlers(m), this.readyState !== f.OPENED || P)throw new n.DOMException(n.DOMException.INVALID_STATE_ERR);
                if (!E())if (t instanceof s)q.ruid = t.ruid, N = t.type; else if (t instanceof l) {
                    if (t.hasBlob()) {
                        var a = t.getBlob();
                        q.ruid = a.ruid, N = a.type
                    }
                } else"string" == typeof t && (M = "UTF-8", N = "text/plain;charset=UTF-8", t = r.utf8_encode(t));
                O = !H && this.upload.hasEventListener(), W = !1, L = !t, H || (P = !0, this.dispatchEvent("readystatechange")), _.call(o, t)
            }, abort: function () {
                var t;
                if (W = !0, H = !1, ~e.inArray(i("readyState"), [f.UNSENT, f.OPENED, f.DONE]))i("readyState", f.UNSENT); else {
                    if (i("readyState", f.DONE), P = !1, G === g)X.abort(), this.dispatchEvent("readystatechange"), this.dispatchEvent("abort"), this.dispatchEvent("loadend"), L || (this.upload.dispatchEvent("abort"), this.upload.dispatchEvent("loadend")); else {
                        if ("function" !== e.typeOf(X.getRuntime) || !(t = X.getRuntime()))throw new n.DOMException(n.DOMException.INVALID_STATE_ERR);
                        t.exec.call(X, "XMLHttpRequest", "abort", L)
                    }
                    L = !0
                }
            }, destroy: function () {
                X && ("function" === e.typeOf(X.destroy) && X.destroy(), X = null), this.unbindAll(), this.upload && (this.upload.unbindAll(), this.upload = null)
            }})
        }

        var p, h = {100: "Continue", 101: "Switching Protocols", 102: "Processing", 200: "OK", 201: "Created", 202: "Accepted", 203: "Non-Authoritative Information", 204: "No Content", 205: "Reset Content", 206: "Partial Content", 207: "Multi-Status", 226: "IM Used", 300: "Multiple Choices", 301: "Moved Permanently", 302: "Found", 303: "See Other", 304: "Not Modified", 305: "Use Proxy", 306: "Reserved", 307: "Temporary Redirect", 400: "Bad Request", 401: "Unauthorized", 402: "Payment Required", 403: "Forbidden", 404: "Not Found", 405: "Method Not Allowed", 406: "Not Acceptable", 407: "Proxy Authentication Required", 408: "Request Timeout", 409: "Conflict", 410: "Gone", 411: "Length Required", 412: "Precondition Failed", 413: "Request Entity Too Large", 414: "Request-URI Too Long", 415: "Unsupported Media Type", 416: "Requested Range Not Satisfiable", 417: "Expectation Failed", 422: "Unprocessable Entity", 423: "Locked", 424: "Failed Dependency", 426: "Upgrade Required", 500: "Internal Server Error", 501: "Not Implemented", 502: "Bad Gateway", 503: "Service Unavailable", 504: "Gateway Timeout", 505: "HTTP Version Not Supported", 506: "Variant Also Negotiates", 507: "Insufficient Storage", 510: "Not Extended"};
        d.prototype = i.instance;
        var m = ["loadstart", "progress", "abort", "error", "load", "timeout", "loadend"], g = 1, v = 2;
        return f.UNSENT = 0, f.OPENED = 1, f.HEADERS_RECEIVED = 2, f.LOADING = 3, f.DONE = 4, f.prototype = i.instance, f
    }), i(mt, [et, pt, ct], function (e, t, n) {
        function i(i, r) {
            var o, a;
            if (r || (r = {}), a = r.type && "" !== r.type ? r.type : t.getFileMime(r.name), r.name)o = r.name.replace(/\\/g, "/"), o = o.substr(o.lastIndexOf("/") + 1); else {
                var s = a.split("/")[0];
                o = e.guid(("" !== s ? s : "file") + "_"), t.extensions[a] && (o += "." + t.extensions[a][0])
            }
            n.apply(this, arguments), e.extend(this, {type: a || "", name: o || e.guid("file_"), lastModifiedDate: r.lastModifiedDate || (new Date).toLocaleString()})
        }

        return i.prototype = n.prototype, i
    }), i(gt, [et, lt, it], function (e, t, n) {
        return function () {
            function i(e, t) {
                if (!t.isDetached()) {
                    var i = this.connectRuntime(t.ruid).exec.call(this, "FileReaderSync", "read", e, t);
                    return this.disconnectRuntime(), i
                }
                var r = t.getSource();
                switch (e) {
                    case"readAsBinaryString":
                        return r;
                    case"readAsDataURL":
                        return"data:" + t.type + ";base64," + n.btoa(r);
                    case"readAsText":
                        for (var o = "", a = 0, s = r.length; s > a; a++)o += String.fromCharCode(r[a]);
                        return o
                }
            }

            t.call(this), e.extend(this, {uid: e.guid("uid_"), readAsBinaryString: function (e) {
                return i.call(this, "readAsBinaryString", e)
            }, readAsDataURL: function (e) {
                return i.call(this, "readAsDataURL", e)
            }, readAsText: function (e) {
                return i.call(this, "readAsText", e)
            }})
        }
    }), i(vt, [et, tt, ct, mt, gt], function (e, t, n, i, r) {
        function o() {
            function o() {
                var t = "";
                return e.each(a, function (i) {
                    if ("string" === e.typeOf(i))t += i; else if (i instanceof n) {
                        var o = new r;
                        t += o.readAsBinaryString(i)
                    }
                }), t
            }

            var a = [];
            e.extend(this, {uid: e.guid("uid_"), append: function (i) {
                if ("string" !== e.typeOf(i) && !(i instanceof n))throw new t.DOMException(t.DOMException.TYPE_MISMATCH_ERR);
                a.push(i)
            }, getBlob: function (e) {
                var t = o(), i;
                return i = new n(null, {size: t.length, type: e}), i.detach(t), i
            }, getFile: function (e, t) {
                var n = o(), r;
                return r = new i(null, {size: n.length, type: e, name: t}), r.detach(n), r
            }, destroy: function () {
                a = []
            }})
        }

        return o
    }), i(xt, [v, g, k, l, Y, K, Z, Q, $, ht, dt, vt, it], function (e, n, i, r, o, a, s, l, u, c, d, f, p) {
        function h(e, t, n) {
            return e > n ? e = n : t > e && (e = t), e
        }

        function m() {
        }

        var g = Math.floor, v = {n: [.5, 0, 0, -1, 0, 1], e: [1, .5, 1, 0, 0, 0], s: [.5, 1, 0, 1, 0, 0], w: [0, .5, -1, 0, 1, 0], nw: [0, 0, -1, -1, 1, 1], ne: [1, 0, 1, -1, 0, 1], se: [1, 1, 1, 1, 0, 0], sw: [0, 1, -1, 1, 1, 0]};
        return e.extend({init: function (e) {
            var t = this;
            t._super(e), t.addClass("imagecanvas"), t._mirror = t._rotation = 0
        }, applyAction: m, endAction: m, renderHtml: function () {
            var e = this, t = e._id, n = e.classPrefix;
            return'<div id="' + t + '" class="' + e.classes() + '">' + '<div id="' + t + '-clip" class="' + n + 'imagecanvas-clip"></div>' + "</div>"
        }, filter: function (e) {
            var t = this;
            return t._currentFilter = t._currentFilter || t._imageEditor.filter(e), t._currentFilter
        }, revert: function () {
            var e = this;
            e._imageEditor.hasCanvasSupport() ? e._imageEditor.revert() : e.loadFromPath(e.path, function () {
                e.tempname = null, e.zoom("fit"), e.fire("change")
            })
        }, undo: function () {
            this._imageEditor.undo()
        }, redo: function () {
            this._imageEditor.redo()
        }, canUndo: function () {
            return this._imageEditor.canUndo() || !!this.tempname
        }, canRedo: function () {
            return this._imageEditor.canRedo()
        }, zoom: function (e) {
            var i = this, r, o, a, s, l, u, c, d, f = 20;
            return e !== t ? .1 > e ? i : (a = i.getEl(), s = a.offsetWidth - 2, l = a.offsetHeight - 2, u = i.size(), "fit" == e && (e = Math.min((s - f) / u.w, (l - f) / u.h), e >= 1 && (e = 1)), i._zoom = e, r = g(u.w * e), o = g(u.h * e), i._zoomPos = c = i._zoomPos || {}, c.x = s > r ? g(s / 2 - r / 2) : 0, c.y = l > o ? g(l / 2 - o / 2) : 0, c.w = r, c.h = o, d = i.getCanvas(), d && n.css(d, {left: c.x, top: c.y, width: r, height: o}), n.css(i.getEl("clip"), {left: c.x, top: c.y, width: r, height: o}), n.css(n.get(i._id + "-img"), {width: r, height: o}), i.repaintCropRect(!0), i) : i._zoom
        }, startTransation: function () {
            this._imageEditor.hasCanvasSupport() && (this.inTransact = !0, this._imageEditor.addUndoLevel(), this._imageEditor.setUndoEnabled(!1))
        }, apply: function () {
            var e = this;
            e._currentFilter && (e._currentFilter.save(), e._currentFilter = null), e.inTransact = !1, e._imageEditor.setUndoEnabled(!0), e.applyAction(), e.applyAction = m
        }, cancel: function () {
            var e = this;
            e._currentFilter && (e._currentFilter.cancel(), e._currentFilter = null), e.inTransact && (e._imageEditor.undo(!0), e.inTransact = !1, e._imageEditor.setUndoEnabled(!0)), e.hasCanvasSupport() || (e._mirror = e._rotation = 0, e.updateOldIE()), e.endAction(), e.applyAction = m
        }, resize: function (e, t) {
            var n = this;
            n.hasCanvasSupport() ? n._imageEditor.resize({w: e, h: t}) : a.exec("alterimage", {action: "alter", resize: {w: e, h: t}, path: n.path, tempname: n.tempname}, function (e) {
                n.loadFromUrl(getMoxieManagerApiPage() + "?act=streamfile&stream=true&path=" + encodeURIComponent(n.path) + "&tempname=true&r=" + (new Date).getTime(), function () {
                    n.zoom("fit"), n.tempname = e.tempname, n.fire("change")
                })
            })
        }, flip: function (e) {
            var t = this;
            t.hasCanvasSupport() ? t._imageEditor.flip(e) : (t._mirror = t._mirror ? 0 : 1, "v" == e && (t._rotation = (t._rotation + 180) % 360), t.updateOldIE()), t.applyAction = function () {
                var e = t._imageEditor;
                e.hasCanvasSupport() || a.exec("alterimage", {action: "alter", rotate: t._rotation, flip: t._mirror ? "h" : null, path: t.path, tempname: t.tempname}, function (e) {
                    t.loadFromUrl(getMoxieManagerApiPage() + "?act=streamfile&stream=true&path=" + encodeURIComponent(t.path) + "&tempname=true&r=" + (new Date).getTime(), function () {
                        t.zoom("fit"), t.tempname = e.tempname, t.fire("change")
                    })
                })
            }
        }, rotate: function (e) {
            var t = this;
            t.hasCanvasSupport() ? t._imageEditor.rotate(e) : (t._rotation = (t._rotation + (0 > e ? 360 + e : e)) % 360, t.updateOldIE()), t.applyAction = function () {
                var e = t._imageEditor;
                e.hasCanvasSupport() || a.exec("alterimage", {action: "alter", rotate: t._rotation, flip: t._mirror ? "h" : null, path: t.path, tempname: t.tempname}, function (e) {
                    t.loadFromUrl(getMoxieManagerApiPage() + "?act=streamfile&stream=true&path=" + encodeURIComponent(t.path) + "&tempname=true&r=" + (new Date).getTime(), function () {
                        t.zoom("fit"), t.tempname = e.tempname, t.fire("change")
                    })
                })
            }
        }, updateOldIE: function () {
            var e = this, t = n.get(e._id + "-img");
            t.style.filter = "progid:DXImageTransform.Microsoft.BasicImage(rotation=" + e._rotation / 90 + ",mirror=" + e._mirror + ");", t.parentNode.style.width = t.offsetWidth + "px", t.parentNode.style.height = t.offsetHeight + "px"
        }, redeye: function (e) {
            var t = this;
            if (e = e || 60, !t._redEyeInit) {
                t.getEl("clip").appendChild(n.createFragment('<div id="' + t._id + '-circle" class="moxman-imagecanvas-circle"></div>'));
                var i = n.getPos(t.getEl("clip"));
                t.getEl("clip").style.overflow = "hidden", t.on("mousemove", function (e) {
                    n.css(t.getEl("circle"), {left: e.clientX - i.x - t._redEyeRadius, top: e.clientY - i.y - t._redEyeRadius})
                }), t._redEyeInit = !0
            }
            n.css(t.getEl("circle"), {width: 2 * e, height: 2 * e, borderRadius: e}), t._redEyeRadius = e
        }, crop: function (e, t, o, s) {
            function u() {
                var e, t = "", i = d._id, o = d.classPrefix;
                for (e in v)t += '<div id="' + i + "-" + e + '" class="' + o + 'imagecanvas-handle" style="cursor:' + e + '-resize"></div>';
                r.each("top right bottom left middle".split(" "), function (e) {
                    t += '<div id="' + i + "-" + e + '" class="moxman-imagecanvas-ants moxman-imagecanvas-' + e + '"></div>'
                }), d.getEl().appendChild(n.createFragment('<div id="' + i + '-overlay" class="moxman-imagecanvas-overlay">' + t + "</div>"));
                var a = d.getEl("img").cloneNode(!0);
                a.id = i + "-mimg", d.getEl("middle").appendChild(a)
            }

            function c() {
                function e(e) {
                    var t = d.getEl(e);
                    d.dragHelper = new i(d._id, {handle: t.id, start: function (e) {
                        x = d.zoom(), f = d._cropRect, r = e.screenX, o = e.screenY, a = f.x, s = f.y, l = f.w, u = f.h;
                        for (var t in v)if (d._id + "-" + t == e.target.id) {
                            c = v[t], m = d.getEl(t);
                            break
                        }
                        n.addClass(m, d.classPrefix + "imagecanvas-handle-selected")
                    }, drag: function (e) {
                        var t, n, i, p, m;
                        i = p = 0, t = g((e.screenX - r) / x), n = g((e.screenY - o) / x), f.x = t * c[4] + a, f.y = n * c[5] + s, 0 > f.x && (i = f.x, f.x = 0), 0 > f.y && (p = f.y, f.y = 0), f.w = t * c[2] + l + i, f.h = n * c[3] + u + p, m = d.size(), f.x + f.w > m.w && (f.w = m.w - f.x, f.x = m.w - f.w), f.y + f.h > m.h && (f.h = m.h - f.y, f.y = m.h - f.h), f.x = h(f.x, 0, a + l), f.y = h(f.y, 0, u + s), f.w = h(f.w, 0, m.w), f.h = h(f.h, 0, m.h), d.repaintCropRect()
                    }, stop: function () {
                        n.removeClass(m, d.classPrefix + "imagecanvas-handle-selected")
                    }})
                }

                var t, r, o, a, s, l, u, c, m, x;
                d.dragHelper = new i(d._id, {handle: d._id + "-mimg", start: function (e) {
                    f = d._cropRect, x = d.zoom(), r = e.screenX, o = e.screenY, a = f.x, s = f.y
                }, drag: function (e) {
                    var t, n;
                    t = g((e.screenX - r) / x), n = g((e.screenY - o) / x), f.x = t + a, f.y = n + s, f.x = h(f.x, 0, p.w - f.w), f.y = h(f.y, 0, p.h - f.h), d.repaintCropRect()
                }});
                for (t in v)e(t)
            }

            var d = this, f = d._cropRect, p = d.size();
            d._cropRect = f = {x: e || 0, y: t || 0, w: o || p.w, h: s || p.h}, f.w = h(f.w, 0, p.w), f.h = h(f.h, 0, p.h), f.x = h(f.x, 0, p.w - f.w), f.y = h(f.y, 0, p.h - f.h), d._cropInit ? d.getEl("mimg").src = d.getEl("img").src : (d._cropInit = !0, u(), c()), d.addClass("imagecanvas-crop"), d.repaintCropRect(!0), d.applyAction = function () {
                var e = d._imageEditor;
                e.hasCanvasSupport() ? e.crop(d._cropRect) : a.exec("alterimage", {action: "alter", crop: d._cropRect, path: d.path, tempname: d.tempname}, function (e) {
                    d.loadFromUrl(getMoxieManagerApiPage() + "?act=streamfile&stream=true&path=" + encodeURIComponent(d.path) + "&tempname=true&r=" + (new Date).getTime(), function () {
                        d.zoom("fit"), d.tempname = e.tempname, d.fire("change")
                    })
                })
            }, d.endAction = function () {
                d.removeClass("imagecanvas-crop")
            }
        }, repaintCropRect: function (e) {
            function t(e, t) {
                n.css(i.getEl(e), {left: t.x, top: t.y, width: t.w, height: t.h})
            }

            var i = this, r = i._cropRect, o, a, s, l, u = i.zoom(), c = i._zoomPos;
            if (r) {
                o = g(r.x * u), a = g(r.y * u), s = g(r.w * u), l = g(r.h * u);
                for (var d in v) {
                    var f = v[d];
                    t(d, {x: s * f[0] + o + c.x, y: l * f[1] + a + c.y})
                }
                if (e) {
                    t("clip", {x: c.x, y: c.y});
                    var p = i.size();
                    t("mimg", {w: g(p.w * u), h: g(p.h * u)}), t("img", {w: g(p.w * u), h: g(p.h * u)})
                }
                t("mimg", {x: -o, y: -a}), t("middle", {x: o + c.x, y: a + c.y, w: s, h: l}), t("top", {x: o + c.x, y: a + c.y, w: s}), t("right", {x: o + s + c.x, y: a + c.y, h: l}), t("bottom", {x: o + c.x, y: a + l + c.y, w: s}), t("left", {x: o + c.x, y: a + c.y, h: l}), i.fire("cropchange", {rect: r})
            }
        }, loadFromPath: function (e, t) {
            this.path = e, this.loadFromUrl(getMoxieManagerApiPage() + "?act=streamfile&stream=true&path=" + encodeURIComponent(e) + "&r=" + (new Date).getTime(), t)
        }, loadFromUrl: function (e, t) {
            var i = this, r, o = i.getEl("clip"), a = n.get(i._id + "-img");
            r = a || new Image, r.onload = function () {
                r.onload = m, i.size({w: r.naturalWidth || r.width || r.clientWidth, h: r.naturalHeight || r.height || r.clientHeight}), o.style.visibility = "", t.call(i, r)
            }, r.src = e, r.id = i._id + "-img", r.style.cssText = "", o.style.visibility = "hidden", a || o.appendChild(r)
        }, saveAs: function (e, t, n) {
            var i = this;
            if (i._imageEditor.hasCanvasSupport()) {
                var r = i._imageEditor.getCanvas().toDataURL(/\.png$/i.test(e) ? "image/png" : "image/jpeg", .8);
                r = p.atob(r.substr(r.indexOf(",") + 1));
                var o = new f;
                o.append(r);
                var h = new d;
                h.append("file", o.getBlob());
                var m = new c;
                m.open("POST", getMoxieManagerApiPage() + "?act=upload&path=" + encodeURIComponent(s.dirname(i.path)) + "&name=" + encodeURIComponent(e) + "&overwrite=true"), m.onload = function () {
                    var e = u.parse(m.responseText);
                    i.fire("change"), e.error ? n(e.error) : t(e.result)
                }, m.send(h)
            } else {
                var g = s.join(s.dirname(i.path), e);
                a.exec("alterimage", {action: "save", path: g, tempname: i.tempname}, function (e) {
                    i.loadFromUrl(getMoxieManagerApiPage() + "?act=streamfile&stream=true&path=" + encodeURIComponent(g) + "&tempname=true&r=" + (new Date).getTime(), function () {
                        i.zoom("fit"), i.tempname = e.tempname, i.fire("change"), t(e)
                    })
                })
            }
        }, size: function (e) {
            var t = this;
            return e ? (t._size = e, t) : t._size
        }, getCanvas: function () {
            var e = this._imageEditor;
            return e ? e.getCanvas() : null
        }, postRender: function () {
            var e = this;
            e._imageEditor = new o({viewport: e._id + "-clip", image: e._id + "-img", onchange: function () {
                e.size(e._imageEditor.getSize()), e.zoom(e.zoom()), e.fire("change")
            }}), e._super()
        }, hasCanvasSupport: function () {
            return this._imageEditor.hasCanvasSupport()
        }, hasWebGlSupport: function () {
            return this._imageEditor.hasWebGlSupport()
        }})
    }), i(yt, [b], function (e) {
        return e.extend({init: function (e) {
            var t = this;
            t._super(e), t.addClass("widget"), t.addClass("label"), t.canFocus = !1, e.multiline && t.addClass("autoscroll"), e.strong && t.addClass("strong")
        }, initLayoutRect: function () {
            var e = this, t = e._super();
            return e.settings.multiline && (e.getEl().offsetWidth > t.maxW && (t.minW = t.maxW, e.addClass("multiline")), e.getEl().style.width = t.minW + "px", t.startMinH = t.h = t.minH = Math.min(t.maxH, e.getEl().offsetHeight)), t
        }, disabled: function (e) {
            var t = this, n;
            return e !== n && (t.toggleClass("label-disabled", e), t._rendered && (t.getEl()[0].className = t.classes())), t._super(e)
        }, repaint: function () {
            var e = this;
            return e.settings.multiline || (e.getEl().style.lineHeight = e.layoutRect().h + "px"), e._super()
        }, text: function (e) {
            var t = this;
            return t._rendered && e && (t.getEl().innerHTML = t.encode(e)), t._super(e)
        }, renderHtml: function () {
            var e = this, t = e.settings.forId;
            return'<label id="' + e._id + '" class="' + e.classes() + '"' + (t ? ' for="' + t : "") + '">' + e.encode(e._text) + "</label>"
        }})
    }), i(bt, [E, M], function (e, t) {
        return e.extend({Defaults: {role: "toolbar", layout: "flow"}, init: function (e) {
            var t = this;
            t._super(e), t.addClass("toolbar")
        }, postRender: function () {
            var e = this;
            return e.items().addClass("toolbar-item"), e.keyNav = new t({root: e, enableLeftRight: !0}), e._super()
        }})
    }), i(wt, [bt], function (e) {
        return e.extend({Defaults: {role: "menubar", containerCls: "menubar", defaults: {type: "menubutton"}}})
    }), i(_t, [w, _, wt], function (e, t, n) {
        function i(e, t) {
            for (; e;) {
                if (t === e)return!0;
                e = e.parentNode
            }
            return!1
        }

        var r = e.extend({init: function (e) {
            var t = this;
            t._renderOpen = !0, t._super(e), t.addClass("menubtn"), t.aria("haspopup", !0), t.hasPopup = !0
        }, showMenu: function () {
            var e = this, n = e.settings, i;
            e.menu || (i = n.menu || [], i.length ? i = {type: "menu", items: i} : i.type = i.type || "menu", e.menu = t.create(i).parent(e).renderTo(e.getContainerElm()), e.fire("createmenu"), e.menu.reflow(), e.menu.on("cancel", function (t) {
                t.control === e.menu && e.focus()
            }), e.menu.on("show hide", function (t) {
                t.control == e.menu && e.activeMenu("show" == t.type)
            }).fire("show"), e.aria("expanded", !0)), e.menu.show(), e.menu.layoutRect({w: e.layoutRect().w}), e.menu.moveRel(e.getEl(), "bl-tl")
        }, hideMenu: function () {
            var e = this;
            e.menu && (e.menu.items().each(function (e) {
                e.hideMenu && e.hideMenu()
            }), e.menu.hide(), e.aria("expanded", !1))
        }, activeMenu: function (e) {
            this.toggleClass("active", e)
        }, renderHtml: function () {
            var e = this, t = e._id, i = e.classPrefix, r = e.settings.icon ? i + "ico " + i + "i-" + e.settings.icon : "";
            return e.aria("role", e.parent()instanceof n ? "menuitem" : "button"), '<div id="' + t + '" class="' + e.classes() + '" tabindex="-1">' + '<button id="' + t + '-open" role="presentation" tabindex="-1">' + (r ? '<i class="' + r + '"></i>' : "") + (e._text ? (r ? " " : "") + e.encode(e._text) : "") + ' <i class="' + i + 'caret"></i>' + "</button>" + "</div>"
        }, postRender: function () {
            var e = this;
            return e.on("click", function (t) {
                t.control === e && i(t.target, e.getEl()) && (e.showMenu(), t.keyboard && e.menu.items()[0].focus())
            }), e.on("mouseenter", function (t) {
                var n = t.control, i = e.parent(), o;
                n && i && n instanceof r && n.parent() == i && (i.items().filter("MenuButton").each(function (e) {
                    e.hideMenu && e != n && (e.menu && e.menu.visible() && (o = !0), e.hideMenu())
                }), o && n.showMenu())
            }), e._super()
        }, text: function (e) {
            var t = this, n, i;
            if (t._rendered)for (i = t.getEl("open").childNodes, n = 0; i.length > n; n++)3 == i[n].nodeType && (i[n].data = e);
            return this._super(e)
        }, remove: function () {
            this._super(), this.menu && this.menu.remove()
        }});
        return r
    }), i(Et, [_t], function (e) {
        return e.extend({init: function (e) {
            var t = this, n, i, r, o;
            if (t._values = n = e.values, n) {
                for (i = 0; n.length > i; i++)r = n[i].selected || e.value === n[i].value, r && (o = o || n[i].text, t._value = n[i].value);
                e.menu = n
            }
            e.text = e.text || o || n[0].text, t._super(e), t.addClass("listbox"), t.on("select", function (n) {
                e.multiple ? n.control.active(!n.control.active()) : t.value(n.control.settings.value)
            })
        }, value: function (e) {
            var n = this, i, r, o, a;
            if (e !== t) {
                if (n.menu)n.menu.items().each(function (t) {
                    i = t.value() === e, i && (r = r || t.text()), t.active(i)
                }); else for (o = n.settings.menu, a = 0; o.length > a; a++)i = o[a].value == e, i && (r = r || o[a].text), o[a].active = i;
                n.text(r)
            }
            return n._super(e)
        }})
    }), i(Ct, [b, _], function (e, t) {
        return e.extend({Defaults: {border: 0, role: "menuitem"}, init: function (e) {
            var t = this;
            t.hasPopup = !0, t._super(e), e = t.settings, t.addClass("menu-item"), e.menu && t.addClass("menu-item-expand"), ("-" === t._text || "|" === t._text) && (t.addClass("menu-item-sep"), t.aria("role", "separator"), t.canFocus = !1, t._text = "-"), e.selectable && (t.aria("role", "menuitemcheckbox"), t.aria("checked", !0), t.addClass("menu-item-checkbox"), e.icon = "selected"), t.on("mousedown", function (e) {
                e.preventDefault()
            }), t.on("mouseenter click", function (n) {
                n.control === t && (e.menu || "click" !== n.type ? (t.showMenu(), n.keyboard && setTimeout(function () {
                    t.menu.items()[0].focus()
                }, 0)) : (t.parent().hideAll(), t.fire("cancel"), t.fire("select")))
            }), e.menu && t.aria("haspopup", !0)
        }, hasMenus: function () {
            return!!this.settings.menu
        }, showMenu: function () {
            var e = this, n = e.settings, i;
            e.parent().items().each(function (t) {
                t !== e && t.hideMenu()
            }), n.menu && (e.menu ? e.menu.show() : (i = n.menu, i.length ? i = {type: "menu", items: i} : i.type = i.type || "menu", e.menu = t.create(i).parent(e).renderTo(e.getContainerElm()), e.menu.reflow(), e.menu.fire("show"), e.menu.on("cancel", function () {
                e.focus()
            }), e.menu.on("hide", function (t) {
                t.control === e.menu && e.removeClass("selected")
            })), e.menu._parentMenu = e.parent(), e.menu.addClass("menu-sub"), e.menu.moveRel(e.getEl(), "tr-tl"), e.addClass("selected"), e.aria("expanded", !0))
        }, hideMenu: function () {
            var e = this;
            return e.menu && (e.menu.items().each(function (e) {
                e.hideMenu && e.hideMenu()
            }), e.menu.hide(), e.aria("expanded", !1)), e
        }, renderHtml: function () {
            var e = this, t = e._id, n = e.settings, i = e.classPrefix, r = e.encode(e._text), o = e.settings.icon;
            return o && e.parent().addClass("menu-has-icons"), o = i + "ico " + i + "i-" + (e.settings.icon || "none"), '<div id="' + t + '" class="' + e.classes() + '" tabindex="-1">' + ("-" !== r ? '<i class="' + o + '"></i>&nbsp;' : "") + ("-" !== r ? '<span id="' + t + '-text" class="' + i + 'text">' + r + "</span>" : "") + (n.shortcut ? '<div id="' + t + '-shortcut" class="' + i + 'menu-shortcut">' + n.shortcut + "</div>" : "") + (n.menu ? '<div class="' + i + 'caret"></div>' : "") + "</div>"
        }, postRender: function () {
            var e = this, t = e.settings, n = t.textStyle;
            if ("function" == typeof n && (n = n()), n) {
                var i = e.getEl("text");
                i && i.setAttribute("style", n)
            }
            return e._super()
        }, remove: function () {
            this._super(), this.menu && this.menu.remove()
        }})
    }), i(Rt, [I, M, Ct], function (e, n, i) {
        var r = e.extend({Defaults: {defaultType: "menuitem", border: 1, layout: "stack", role: "menu"}, init: function (e) {
            var t = this;
            e.autohide = !0, t._super(e), t.addClass("menu"), t.keyNav = new n({root: t, enableUpDown: !0, enableLeftRight: !0, leftAction: function () {
                t.parent()instanceof i && t.keyNav.cancel()
            }, onCancel: function () {
                t.fire("cancel", {}, !1), t.hide()
            }})
        }, repaint: function () {
            return this.toggleClass("menu-align", !0), this._super(), this.getEl().style.height = "", this.getEl("body").style.height = "", this
        }, cancel: function () {
            var e = this;
            e.hideAll(), e.fire("cancel"), e.fire("select")
        }, hideAll: function () {
            var e = this;
            return this.find("menuitem").exec("hideMenu"), e._super()
        }, preRender: function () {
            var e = this;
            return e.items().each(function (n) {
                var i = n.settings;
                return i.icon || i.selectable ? (e._hasIcons = !0, !1) : t
            }), e._super()
        }});
        return r
    }), i(Tt, [I, D, g, M, k], function (e, n, i, r, o) {
        var a = e.extend({modal: !0, Defaults: {border: 1, layout: "flex", containerCls: "panel", role: "dialog", callbacks: {submit: function () {
            this.fire("submit", {data: this.toJSON()})
        }, close: function () {
            this.close()
        }}}, init: function (e) {
            var t = this;
            t._super(e), t.addClass("window"), e.buttons && (t.statusbar = new n({layout: "flex", border: "1 0 0 0", spacing: 3, padding: 10, align: "center", pack: "end", defaults: {type: "button"}, items: e.buttons}), t.statusbar.addClass("foot"), t.statusbar.parent(t)), t.on("click", function (e) {
                -1 != e.target.className.indexOf(t.classPrefix + "close") && t.close()
            }), t.aria("label", e.title), t._fullscreen = !1
        }, recalc: function () {
            var e = this, n = e.statusbar, r, o, a;
            return e._fullscreen && (e.layoutRect(i.getWindowSize()), e.layoutRect().contentH = e.layoutRect().innerH), e._super(), r = e.layoutRect(), e.settings.title && !e._fullscreen && (o = r.headerW, o > r.minW && (r.minW = r.w = o + (r.w - r.innerW), r.innerW = r.w - r.deltaW, a = !0)), n && (n.layoutRect({w: e.layoutRect().innerW}).recalc(), o = n.layoutRect().minW + r.deltaW, o > r.minW && (r.minW = r.w = o, r.innerW = r.w - r.deltaW, a = !0)), a ? (e.recalc(), t) : t
        }, initLayoutRect: function () {
            var e = this, t = e._super(), n = 0, r;
            e.settings.title && !e._fullscreen && (r = e.getEl("head"), t.headerW = r.offsetWidth, t.headerH = r.offsetHeight, n += t.headerH), e.statusbar && (n += e.statusbar.layoutRect().h), t.deltaH += n, t.minH += n, t.h += n;
            var o = i.getWindowSize();
            return t.x = Math.max(0, o.w / 2 - t.w / 2), t.y = Math.max(0, o.h / 2 - t.h / 2), t
        }, renderHtml: function () {
            var e = this, n = e._layout, i = e._id, r = e.classPrefix, o = e.settings, a = "", s = "", l = o.html;
            return e.preRender(), n.preRender(e), o.title && (a = '<div id="' + i + '-head" class="' + r + 'window-head">' + '<div class="' + r + 'title">' + e.encode(o.title) + "</div>" + '<button type="button" class="' + r + 'close" aria-hidden="true">&times;</button>' + '<div id="' + i + '-dragh" class="' + r + 'dragh"></div>' + "</div>"), o.url && (l = '<iframe src="' + o.url + '" tabindex="-1"></iframe>'), l === t && (l = n.renderHtml(e)), e.statusbar && (s = e.statusbar.renderHtml()), '<div id="' + i + '" class="' + e.classes() + '" hideFocus="1" tabIndex="-1">' + a + '<div id="' + i + '-body" class="' + e.classes("body") + '">' + l + "</div>" + s + "</div>"
        }, fullscreen: function (e) {
            var t = this, n = document.documentElement, r, o = t.classPrefix, a;
            if (e != t._fullscreen)if (i.on(window, "resize", function () {
                var e;
                if (t._fullscreen)if (r)t._timer || (t._timer = setTimeout(function () {
                    var e = i.getWindowSize();
                    t.moveTo(0, 0).resizeTo(e.w, e.h), t._timer = 0
                }, 50)); else {
                    e = (new Date).getTime();
                    var n = i.getWindowSize();
                    t.moveTo(0, 0).resizeTo(n.w, n.h), (new Date).getTime() - e > 50 && (r = !0)
                }
            }), a = t.layoutRect(), t._fullscreen = e, e) {
                t._initial = {x: a.x, y: a.y, w: a.w, h: a.h}, t._borderBox = t.parseBox("0"), t.getEl("head").style.display = "none", a.deltaH -= a.headerH + 2, i.addClass(n, o + "fullscreen"), i.addClass(document.body, o + "fullscreen"), t.addClass("fullscreen");
                var s = i.getWindowSize();
                t.moveTo(0, 0).resizeTo(s.w, s.h)
            } else t._borderBox = t.parseBox(t.settings.border), t.getEl("head").style.display = "", a.deltaH += a.headerH, i.removeClass(n, o + "fullscreen"), i.removeClass(document.body, o + "fullscreen"), t.removeClass("fullscreen"), t.moveTo(t._initial.x, t._initial.y).resizeTo(t._initial.w, t._initial.h);
            return t.reflow()
        }, postRender: function () {
            var e = this, t = [], n, i, a;
            setTimeout(function () {
                e.addClass("in")
            }, 0), e.find("*").each(function (e) {
                e.canFocus && (i = i || e.settings.autofocus, n = n || e, "filepicker" == e.type ? (t.push(e.getEl("inp")), e.getEl("open") && t.push(e.getEl("open").firstChild)) : t.push(e.getEl()))
            }), e.statusbar && e.statusbar.find("*").each(function (e) {
                e.canFocus && (i = i || e.settings.autofocus, n = n || e, t.push(e.getEl()))
            }), e.keyboardNavigation = new r({root: e, enableLeftRight: !1, enableUpDown: !1, items: t, onCancel: function () {
                e.close()
            }}), e._super(), e.statusbar && e.statusbar.postRender(), !i && n && n.focus(), this.dragHelger = new o(e._id + "-dragh", {start: function () {
                a = {x: e.layoutRect().x, y: e.layoutRect().y}
            }, drag: function (t) {
                e.moveTo(a.x + t.deltaX, a.y + t.deltaY)
            }}), e.on("submit", function (t) {
                t.isDefaultPrevented() || e.close()
            })
        }, submit: function () {
            return this.fire("submit", {data: this.toJSON()})
        }, remove: function () {
            var e = this;
            e._super(), e.dragHelger.destroy(), i.removeClass(document.documentElement, e.classPrefix + "fullscreen"), i.removeClass(document.body, e.classPrefix + "fullscreen"), e.statusbar && this.statusbar.remove()
        }});
        return a
    }), i(At, [Tt], function (e) {
        var t = e.extend({init: function (e) {
            e = {border: 1, padding: 20, layout: "flex", pack: "center", align: "center", containerCls: "panel", autoScroll: !0, buttons: {type: "button", text: "Ok", action: "ok"}, items: {type: "label", multiline: !0, maxWidth: 500, maxHeight: 200}}, this._super(e)
        }, Statics: {OK: 1, OK_CANCEL: 2, YES_NO: 3, YES_NO_CANCEL: 4, msgBox: function (n) {
            var i, r = n.callback || function () {
            };
            switch (n.buttons) {
                case t.OK_CANCEL:
                    i = [
                        {type: "button", text: "Ok", subtype: "primary", onClick: function (e) {
                            e.control.parents()[1].close(), r(!0)
                        }},
                        {type: "button", text: "Cancel", onClick: function (e) {
                            e.control.parents()[1].close(), r(!1)
                        }}
                    ];
                    break;
                case t.YES_NO:
                    i = [
                        {type: "button", text: "Ok", subtype: "primary", onClick: function (e) {
                            e.control.parents()[1].close(), r(!0)
                        }}
                    ];
                    break;
                case t.YES_NO_CANCEL:
                    i = [
                        {type: "button", text: "Ok", subtype: "primary", onClick: function (e) {
                            e.control.parents()[1].close()
                        }}
                    ];
                    break;
                default:
                    i = [
                        {type: "button", text: "Ok", subtype: "primary", onClick: function (e) {
                            e.control.parents()[1].close()
                        }}
                    ]
            }
            return new e({padding: 20, x: n.x, y: n.y, minWidth: 300, minHeight: 100, layout: "flex", pack: "center", align: "center", buttons: i, title: n.title, items: {type: "label", multiline: !0, maxWidth: 500, maxHeight: 200, text: n.text}, onClose: n.onClose}).renderTo(document.body).reflow()
        }, alert: function (e, n) {
            return"string" == typeof e && (e = {text: e}), e.callback = n, t.msgBox(e)
        }, confirm: function (e, n) {
            return"string" == typeof e && (e = {text: e}), e.callback = n, e.buttons = t.OK_CANCEL, t.msgBox(e)
        }}});
        return t
    }), i(kt, [w, I], function (e, t) {
        var n = e.extend({showPanel: function () {
            var e = this, n = e.settings;
            n.panel.popover = !0, e.active(!0), e.panel ? e.panel.show() : e.panel = new t(n.panel).on("hide", function () {
                e.active(!1)
            }).parent(e).renderTo(e.getContainerElm()).reflow().moveRel(e.getEl(), n.popoverAlign || "bc-tc")
        }, hidePanel: function () {
            var e = this;
            e.panel && e.panel.hide()
        }, postRender: function () {
            var e = this;
            return e.on("click", function (t) {
                t.control === e && e.showPanel()
            }), e._super()
        }});
        return n
    }), i(St, [v, M], function (e, n) {
        return e.extend({Defaults: {delimiter: "\u00bb"}, init: function (e) {
            var t = this;
            t._super(e), t.addClass("path"), t.canFocus = !0, t.on("click", function (e) {
                var n, i = e.target;
                (n = i.getAttribute("data-index")) && t.fire("select", {value: t.data()[n], index: n})
            })
        }, focus: function () {
            var e = this;
            return e.keyNav = new n({root: e, enableLeftRight: !0}), e.keyNav.focusFirst(), e
        }, data: function (e) {
            var n = this;
            return e !== t ? (n._data = e, n.update(), n) : n._data
        }, update: function () {
            var e = this, t = e._data, n, i, r = "", o = e.classPrefix;
            for (n = 0, i = t.length; i > n; n++)r += (n > 0 ? '<div class="' + o + 'divider" aria-hidden="true"> ' + e.settings.delimiter + " </div>" : "") + '<div role="button" class="' + o + "path-item" + (n == i - 1 ? " " + o + "last" : "") + '" data-index="' + n + '" tabindex="-1" id="' + e._id + "-" + n + '">' + t[n].name + "</div>";
            e.getEl().innerHTML = r
        }, postRender: function () {
            var e = this;
            e._super(), e.data(e.settings.data)
        }, repa2int: function () {
            var e = this;
            return e.getEl("text").style.lineHeight = e.layoutRect().h + "px", e._super()
        }, renderHtml: function () {
            var e = this, t = e.classPrefix;
            return'<div id="' + e._id + '" class="' + t + 'path">' + '<div class="' + t + 'path-item">&nbsp;</div>' + "</div>"
        }})
    }), i(Dt, [b], function (e) {
        return e.extend({init: function (e) {
            var t = this;
            t._super(e), t.addClass("progress")
        }, value: function (e) {
            var n = this;
            return e !== t ? (n._value = e, n._rendered && (n.getEl().firstChild.innerHTML = e + "%", n.getEl().lastChild.style.width = e + "%"), n) : n._value
        }, renderHtml: function () {
            var e = this, t = e._id, n = this.classPrefix;
            return'<div id="' + t + '" class="' + e.classes() + '">' + '<div class="' + n + 'text">0%</div>' + '<div class="' + n + 'bar"></div>' + "</div>"
        }, postRender: function () {
            var e = this;
            return e._super(), e.value(e.settings.value), e
        }})
    }), i(Ft, [b], function (e) {
        return e.extend({Defaults: {classes: "radio", checked: !1}, init: function (e) {
            var t = this;
            t._super(e), t.on("click", function (e) {
                e.preventDefault(), t.disabled() || t.checked() || (t.parent().items().filter("radio:checked").exec("checked", !1), t.checked(!0))
            }), t.checked(e.checked)
        }, checked: function (e) {
            var n = this;
            return e !== t ? (e ? n.addClass("checked") : n.removeClass("checked"), n._checked = e, n) : n._checked
        }, renderHtml: function () {
            var e = this, t = e._id, n = e.classPrefix;
            return'<div id="' + t + '" class="' + e.classes() + '" role="radio" unselectable="on">' + '<i class="' + n + "ico " + n + 'radio"></i>' + e._text + "</div>"
        }})
    }), i(It, [E], function (e) {
        return e.extend({})
    }), i(Mt, [b, k, g], function (e, n, i) {
        function r(e, t, n) {
            return t > e && (e = t), e > n && (e = n), e
        }

        return e.extend({init: function (e) {
            var n = this;
            e.previewFilter || (e.previewFilter = function (e) {
                return Math.round(100 * e) / 100
            }), n._super(e), n.addClass("slider"), "v" == e.orientation && n.addClass("vertical"), n._minValue = e.minValue || 0, n._maxValue = e.maxValue || 100, n.value(e.value !== t ? e.value : 0), n._initValue = n._value
        }, renderHtml: function () {
            var e = this, t = e._id, n = e.classPrefix;
            return'<div id="' + t + '" class="' + e.classes() + '">' + '<div id="' + t + '-handle" class="' + n + 'slider-handle"></div>' + "</div>"
        }, reset: function () {
            this.value(this._initValue).repaint()
        }, value: function (e) {
            var n = this;
            return e !== t ? (n.fire("change"), n._value = r(e, n._minValue, n._maxValue), n) : n._value
        }, postRender: function () {
            var e = this, t, o, a = 0, s, l, u, c, d, f, p, h;
            l = e._minValue, u = e._maxValue, s = e.value(), "v" == e.settings.orientation ? (d = "screenY", f = "top", p = "height", h = "h") : (d = "screenX", f = "left", p = "width", h = "w"), e._super(), e._dragHelper = new n(e._id, {handle: e._id + "-handle", start: function (n) {
                t = n[d], o = parseInt(e.getEl("handle").style[f], 10), c = (e.layoutRect()[h] || 100) - i.getSize(e.getEl("handle"))[p], e.fire("dragstart", {value: s})
            }, drag: function (n) {
                var i = n[d] - t, p = e.getEl("handle");
                a = r(o + i, 0, c), p.style[f] = a + "px", s = l + a / c * (u - l), e.value(s), e.tooltip().text("" + e.settings.previewFilter(s)).show().moveRel(p, "bc tc"), e.fire("drag", {value: s})
            }, stop: function () {
                e.tooltip().hide(), e.fire("dragend", {value: s})
            }})
        }, repaint: function () {
            var e = this, t, n, r, o;
            "v" == e.settings.orientation ? (o = "top", r = "height", n = "h") : (o = "left", r = "width", n = "w"), t = (e.layoutRect()[n] || 100) - i.getSize(e.getEl("handle"))[r], e._super(), e.getEl("handle").style[o] = t * ((e.value() - e._minValue) / (e._maxValue - e._minValue)) + "px", e.getEl("handle").style.height = e.layoutRect().h + "px"
        }})
    }), i(Nt, [b], function (e) {
        return e.extend({renderHtml: function () {
            var e = this;
            return e.addClass("spacer"), e.canFocus = !1, '<div id="' + e._id + '" class="' + e.classes() + '"></div>'
        }})
    }), i(Ht, [_t, g], function (e, t) {
        var n = t.DOM;
        return e.extend({Defaults: {classes: "widget btn splitbtn", role: "splitbutton"}, repaint: function () {
            var e = this, t = e.getEl(), i = e.layoutRect(), r, o, a;
            return e._super(), r = t.firstChild, o = t.lastChild, n.css(r, {width: i.w - o.offsetWidth, height: i.h - 2}), n.css(o, {height: i.h - 2}), a = r.firstChild.style, a.width = a.height = "100%", a = o.firstChild.style, a.width = a.height = "100%", e
        }, activeMenu: function (e) {
            var t = this;
            n.toggleClass(t.getEl().lastChild, t.classPrefix + "active", e)
        }, renderHtml: function () {
            var e = this, t = e._id, n = e.classPrefix, i = e.settings.icon ? n + "ico " + n + "i-" + e.settings.icon : "";
            return'<div id="' + t + '" class="' + e.classes() + '">' + '<button hidefocus tabindex="-1">' + (i ? '<i class="' + i + '"></i>' : "") + (e._text ? (i ? " " : "") + e._text : "") + "</button>" + '<button class="' + n + 'open" hidefocus tabindex="-1">' + (e._menuBtnText ? (i ? " " : "") + e._menuBtnText : "") + ' <i class="' + n + 'caret"></i>' + "</button>" + "</div>"
        }, postRender: function () {
            var e = this, t = e.settings.onclick;
            return e.on("click", function (e) {
                e.control != this || n.getParent(e.target, "." + this.classPrefix + "open") || (e.stopImmediatePropagation(), t.call(this, e))
            }), delete e.settings.onclick, e._super()
        }})
    }), i(Pt, [B], function (e) {
        return e.extend({Defaults: {containerClass: "stack-layout", controlClass: "stack-layout-item", endClass: "break"}})
    }), i(Ot, [D, g], function (e, t) {
        "use stict";
        return e.extend({lastIdx: 0, Defaults: {layout: "absolute", defaults: {type: "panel"}}, activateTab: function (e) {
            this.activeTabId && t.removeClass(this.getEl(this.activeTabId), this.classPrefix + "active"), this.activeTabId = "t" + e, t.addClass(this.getEl("t" + e), this.classPrefix + "active"), e != this.lastIdx && (this.items()[this.lastIdx].hide(), this.lastIdx = e), this.items()[e].show().fire("showtab"), this.reflow()
        }, renderHtml: function () {
            var e = this, t = e._layout, n = "", i = e.classPrefix;
            return e.preRender(), t.preRender(e), e.items().each(function (t, r) {
                n += '<div id="' + e._id + "-t" + r + '" class="' + i + 'tab" unselectable="on">' + t.settings.title + "</div>"
            }), '<div id="' + e._id + '" class="' + e.classes() + '" hideFocus="1" tabIndex="-1">' + '<div id="' + e._id + '-head" class="' + i + 'tabs">' + n + "</div>" + '<div id="' + e._id + '-body" class="' + e.classes("body") + '">' + t.renderHtml(e) + "</div>" + "</div>"
        }, postRender: function () {
            var e = this;
            e._super(), e.settings.activeTab = e.settings.activeTab || 0, e.activateTab(e.settings.activeTab), this.on("click", function (t) {
                var n = t.target.parentNode;
                if (t.target.parentNode.id == e._id + "-head")for (var i = n.childNodes.length; i--;)n.childNodes[i] == t.target && e.activateTab(i)
            })
        }, initLayoutRect: function () {
            var e = this, t, n, i;
            n = i = 0, e.items().each(function (t, r) {
                n = Math.max(n, t.layoutRect().minW), i = Math.max(i, t.layoutRect().minH), e.settings.activeTab != r && t.hide()
            }), e.items().each(function (e) {
                e.settings.x = 0, e.settings.y = 0, e.settings.w = n, e.settings.h = i, e.layoutRect({x: 0, y: 0, w: n, h: i})
            });
            var r = e.getEl("head").offsetHeight;
            return e.settings.minWidth = n, e.settings.minHeight = i + r, t = e._super(), t.deltaH += e.getEl("head").offsetHeight, t.innerH = t.h - t.deltaH, t
        }})
    }), i(Lt, [b, g], function (e, n) {
        return e.extend({init: function (e) {
            var n = this;
            n._super(e), n._value = e.value || "", n.addClass("textbox"), e.multiline ? n.addClass("multiline") : n.on("keydown", function (e) {
                13 == e.keyCode && n.parents().reverse().each(function (n) {
                    return e.preventDefault(), n.submit ? (n.submit(), !1) : t
                })
            })
        }, value: function (e) {
            var n = this;
            return e !== t ? (n._value = e, n._rendered && (n.getEl().value = e), n) : n._rendered ? n.getEl().value : n._value
        }, repaint: function () {
            var e = this, t, n, i, r = 0, o = 0, a;
            return t = e.getEl().style, n = e._layoutRect, a = e._lastRepaintRect || {}, i = e._borderBox, r = i.left + i.right + 8, o = i.top + i.bottom + (e.settings.multiline ? 8 : 0), n.x !== a.x && (t.left = n.x + "px", a.x = n.x), n.y !== a.y && (t.top = n.y + "px", a.y = n.y), n.w !== a.w && (t.width = n.w - r + "px", a.w = n.w), n.h !== a.h && (t.height = n.h - o + "px", a.h = n.h), e._lastRepaintRect = a, e.fire("repaint", {}, !1), e
        }, renderHtml: function () {
            var e = this, t = e._id, n = e.settings, i = e.encode(e._value, !1), r = "";
            return"spellcheck"in n && (r += ' spellcheck="' + n.spellcheck + '"'), n.maxLength && (r += ' maxlength="' + n.maxLength + '"'), n.size && (r += ' size="' + n.size + '"'), n.subtype && (r += ' type="' + n.subtype + '"'), n.multiline ? '<textarea id="' + t + '" class="' + e.classes() + '" ' + (n.rows ? ' rows="' + n.rows + '"' : "") + ' hidefocus="true"' + r + ">" + i + "</textarea>" : '<input id="' + t + '" class="' + e.classes() + '" value="' + i + '" hidefocus="true"' + r + ">"
        }, postRender: function () {
            var e = this;
            return n.on(e.getEl(), "change", function (t) {
                e.fire("change", t)
            }), e._super()
        }})
    }), i(Wt, [g, v], function (e, t) {
        return function (n) {
            var i = this, r;
            i.show = function (o) {
                return i.hide(), r = !0, window.setTimeout(function () {
                    r && n.appendChild(e.createFragment('<div class="' + t.classPrefix + 'throbber"></div>'))
                }, o || 0), i
            }, i.hide = function () {
                var e = n.lastChild;
                return e && -1 != e.className.indexOf("throbber") && e.parentNode.removeChild(e), r = !1, i
            }
        }
    }), i(zt, [E, g, l, Q], function (e, n, i, r) {
        return e.extend({Defaults: {layout: "flow", classes: "thumbnailview"}, init: function (e) {
            var t = this;
            t._super(e), t.on("click", function (e) {
                var r, o, a, s = e.target;
                do {
                    if (-1 != s.className.indexOf("info")) {
                        if (s.parentNode.getAttribute("data-moxman-isparent"))break;
                        a = s.parentNode, o = i.inArray(a, a.parentNode.childNodes), r = !t.isSelected(o), t.setRowState(o, o, r);
                        break
                    }
                    if (n.hasClass(s, "moxman-image")) {
                        a = s, o = i.inArray(a, a.parentNode.childNodes);
                        var l = t.data()[o];
                        t.fire("thumbclick", {cellIndex: 1, row: l, value: l.name, rowIndex: o, inValue: !0})
                    }
                    s = s.parentNode
                } while (s != t.getEl())
            }), t.on("show", function () {
                t.update()
            })
        }, data: function (e) {
            var n = this;
            return e !== t ? (n._data = e, n._selected = {}, n.update(), n) : n._data
        }, isSelected: function (e) {
            return!!this._selected[e]
        }, selected: function () {
            var e = this, t = e._data, n, i, r = e._selected, o = [];
            for (n = 0, i = t.length; i > n; n++)r[n] && o.push(t[n]);
            return o
        }, unselect: function (e, t) {
            return this.setRowState(e, t, !1)
        }, select: function (e, t) {
            return this.setRowState(e, t, !0)
        }, setRowState: function (e, i, r) {
            var o = this, a, s, l, u;
            e === t && i === t ? (e = 0, i = o._data.length) : i === t && (i = e), u = o._data.length - 1, e = e ? e > u ? u : e : 0, i = i ? i > u ? u : i : 0, s = o._selected, s || (o._selected = s = {}), e > i && (l = e, e = i, i = l);
            var c;
            for (o._rendered && (c = o.getEl("body").childNodes), a = e; i >= a; a++)s[a] = r, c && n.toggleClass(c[a], o.classPrefix + "checked", r);
            return o.fire("select", {selected: o.selected()}), o
        }, update: function () {
            function e(e, t, n) {
                function i(e, i) {
                    return e = parseFloat(e[t]), i = parseFloat(i[t]), e = isNaN(e) ? 0 : e, i = isNaN(i) ? 0 : i, n ? e - i : i - e
                }

                function r(e, i) {
                    return e = ("" + e[t]).toLowerCase(), i = ("" + i[t]).toLowerCase(), n ? e.localeCompare(i) : i.localeCompare(e)
                }

                return e.length && (e = e.sort("number" == typeof e[0][t] ? i : r)), e
            }

            var t = this, n = "", i, o, a = t.data(), s = t.settings, l = t.classPrefix;
            if (t.visible()) {
                for (s.sortBy && (a = e(a, t._sortBy, t._sortOrder)), a = t.fire("beforeUpdate", {data: a}).data, t._data = a, i = 0; a.length > i; i++) {
                    o = a[i];
                    var u = {row: o};
                    s.filter && s.filter(u);
                    var c = u.icon, d = o.path;
                    o.info && o.info.link && (d = o.info.link);
                    var f = getMoxieManagerApiPage() + "?act=streamfile&path=" + encodeURIComponent(d) + "&thumb=true&u=" + o.size;
                    n += '<div class="' + l + 'image" unselectable="on" data-moxman-isparent="' + (u.row.isParent ? "parent" : "") + '">' + (o.thumbnail ? '<img src="' + f + '" />' : "") + (c && !o.thumbnail ? '<i class="' + l + "ico " + l + "thumb " + l + "i-" + c + '"></i>' : "") + '<div class="' + l + 'info" title="' + u.row.name + '">' + (u.row.isParent ? "" : '<i class="' + l + "ico " + l + 'i-checkbox"></i>') + u.row.name + "</div>" + "</div>"
                }
                t.getEl("body").innerHTML = n
            }
        }, postRender: function () {
            var e = this;
            return e._super(), e.data(e.settings.data || []), e
        }})
    }), i(Bt, [E], function (e) {
        var t = e.extend({Defaults: {defaultType: "treeitem", layout: "stack", expanded: !1}, init: function (e) {
            this._super(e), e = this.settings, this.addClass("treeitem"), this.addClass("treeitem-body", "body"), this.addClass("treeitem-title", "title"), (e.expanded || e.fixed) && this.open(), e.fixed && this.addClass("treeitem-title-fixed", "title")
        }, open: function () {
            this.addClass("treeitem-expanded", "body"), this.addClass("treeitem-title-expanded", "title"), this.expanded = !0
        }, close: function () {
            this.removeClass("treeitem-expanded", "body"), this.removeClass("treeitem-title-expanded", "title"), this.expanded = !1, this.find("treeitem").exec("close")
        }, selected: function (e) {
            this.removeClass("treeitem-title-selected", "title"), e && this.addClass("treeitem-title-selected", "title")
        }, renderHtml: function () {
            var e = this, n = e._layout, i = e.settings, r, o, a = "", s = e.classPrefix;
            for (e.preRender(), n.preRender(e), o = e, r = 0; o && o instanceof t;)r++, o.depth = r, o = o.parent();
            return i.icon ? a = '<i class="' + s + "ico " + s + "i-" + i.icon + '"></i>' : i.iconUrl && (a = '<i class="' + s + 'ico" style="background-image: url(\'' + i.iconUrl + "')\"></i>"), '<span id="' + e._id + '" class="' + e.classes() + '"><span id="' + e._id + '-title" class="' + e.classes("title") + '" style="padding-left: ' + 10 * r + 'px">' + '<i class="' + s + "expander " + (e.items().length ? "" : s + "hidden") + '"></i>' + a + '<span id="' + e._id + '-text" class="' + s + 'text" unselectable="true">' + e.encode(e._text) + "</span>" + "</span>" + '<span id="' + e._id + '-body" class="' + e.classes("body") + '">' + n.renderHtml(e) + "</span></span>"
        }});
        return t
    }), i(Ut, [D, Bt], function (e, t) {
        return e.extend({Defaults: {defaultType: "treeitem", layout: "stack"}, init: function (e) {
            var n = this;
            n._super(e), n.on("click", function (e) {
                var i = e.control;
                i instanceof t && (e.preventDefault(), i.settings.fixed || (n.find("treeitem").removeClass("treeitem-title-selected", "title"), i.addClass("treeitem-title-selected", "title"), i.items().length > 0 && (i.expanded ? i.close() : i.open())))
            })
        }})
    }), i(jt, [u], function (e) {
        return e.extend({render: function () {
        }})
    }), i(Vt, [E, g], function (e, t) {
        return e.extend({Defaults: {defaultType: "panel"}, init: function (e) {
            var t = this;
            this._super(e), t.addClass("viewport")
        }, postRender: function () {
            var e = this, n = document.documentElement, i, r = e.classPrefix;
            t.addClass(n, r + "viewport"), t.addClass(document.body, r + "viewport"), t.on(window, "resize", function () {
                var t;
                i ? e._timer || (e._timer = setTimeout(function () {
                    e.reflow(), e._timer = 0
                }, 50)) : (t = (new Date).getTime(), e.reflow(), (new Date).getTime() - t > 50 && (i = !0))
            }), e._super(), e.reflow()
        }, recalc: function () {
            var e = this, n = t.getWindowSize();
            return e.layoutRect(n), e._super()
        }, remove: function () {
            var e = document, n = this.classPrefix;
            return t.removeClass(e.documentElement, n + "viewport"), t.removeClass(e.body, n + "viewport"), this._super()
        }})
    }), i(qt, [], function () {
        var e = "January February March April May June July August September October November December".split(" "), t = "Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec".split(" "), n = "Sunday Monday Tuesday Wednesday Thursday Friday Saturday".split(" "), i = "Sun Mon Tue Wed Thu Fri Sat".split(" ");
        return{format: function (r, o) {
            function a(e) {
                return(10 > e ? "0" : "") + e
            }

            function s(e, t) {
                o = o.replace(e, t)
            }

            return r.getTime || (r = new Date(r)), o = o || "%Y-%d-%m %H:%M:%S", s("%D", "%m/%d/%Y"), s("%r", "%I:%M:%S %p"), s("%Y", "" + r.getFullYear()), s("%y", "" + r.getYear()), s("%m", a(r.getMonth() + 1)), s("%d", a(r.getDate())), s("%H", "" + a(r.getHours())), s("%M", "" + a(r.getMinutes())), s("%S", "" + a(r.getSeconds())), s("%I", "" + ((r.getHours() + 11) % 12 + 1)), s("%p", "" + (12 > r.getHours() ? "AM" : "PM")), s("%B", "" + e[r.getMonth()]), s("%b", "" + t[r.getMonth()]), s("%A", "" + n[r.getDay()]), s("%a", "" + i[r.getDay()]), s("%%", "%"), o
        }}
    }), i(Xt, [], function () {
        function e(e, n) {
            var i, r, o;
            for (n = n.split(" "), o = n.length; o--;) {
                if (i = n[o], r = e[i], r !== t)return r;
                i = i.substr(0, 1).toUpperCase() + i.substr(1);
                for (var a = "o ms moz webkit".split(" "), s = a.length; s--;)if (r = e[a[s] + i], r !== t)return r
            }
        }

        return{canFullScreen: function () {
            return e(document, "fullScreenEnabled FullscreenEnabled") === !0
        }, isFullScreen: function () {
            return e(document, "isFullScreen fullScreen")
        }, requestFullScreen: function (t) {
            t = t || document.body, e(t, "requestFullScreen").call(t)
        }, cancelFullScreen: function () {
            e(document, "cancelFullScreen").call(document)
        }}
    }), i(Gt, [], function () {
        function e(e) {
            for (var t = window, n = e.split(/\//), i = 0; n.length > i; ++i) {
                if (!t[n[i]])return;
                t = t[n[i]]
            }
            return t
        }

        var n = e("moxman/util/I18n");
        if (n)return n;
        var i = {};
        return{add: function (e, t) {
            for (var n in t)i[n] = t[n]
        }, translate: function (e) {
            if (e === t)return e;
            if ("string" != typeof e && e.raw)return e.raw;
            if (e.push) {
                var n = e.slice(1);
                e = (i[e[0]] || e[0]).replace(/\{([^\}]+)\}/g, function (e, t) {
                    return n[t]
                })
            }
            return i[e] || e
        }, data: i}
    }), i(Yt, [l], function (e) {
        function n(e, t) {
            function n(n) {
                function o(o) {
                    a.parentNode.removeChild(a), i[n] = o, r++ == e.length - 1 && t(i)
                }

                var a = new Image;
                a.onload = function () {
                    o({url: e[n], width: a.clientWidth, height: a.clientHeight})
                }, a.onerror = function () {
                    o({url: e[n], width: -1, height: -1})
                }, a.src = e[n];
                var s = a.style;
                s.visibility = "hidden", s.position = "fixed", s.bottom = s.left = 0, document.body.appendChild(a)
            }

            for (var i = Array(e.length), r = 0, o = 0; e.length > o; o++)n(o)
        }

        function i(i, r) {
            var o = [];
            return e.each(i, function (e) {
                var t = /\.(gif|jpe?g|png)$/i.test(e.name);
                t && (e.meta.thumb_url && !e.meta.thumb_width && o.push(e.meta.thumb_url), e.meta.width || o.push(e.meta.url)), e.meta.thumb_url = e.meta.thumb_url || e.meta.url
            }), o.length ? (n(o, function (e) {
                for (var t = 0; e.length > t; t++)for (var n = 0; i.length > n; n++)e[t].url == i[n].meta.url && (i[n].meta.width = e[t].width, i[n].meta.height = e[t].height), e[t].url == i[n].meta.thumb_url && (i[n].meta.thumb_width = e[t].width, i[n].meta.thumb_height = e[t].height);
                r(i)
            }), t) : (r(i), t)
        }

        return{populateImageSizes: i}
    }), i($t, [], function () {
        function e() {
        }

        function n(e) {
            document.getElementsByTagName("head")[0].appendChild(e)
        }

        var i = 0, r = {}, o = {maxLoadTime: 5, load: function (e, t, n) {
            function i() {
                s.length ? o.loadScript(s.shift(), i, n) : r()
            }

            function r() {
                a.length ? o.loadCss(a.shift(), r, n) : t()
            }

            var a = e.css || [], s = e.js || [];
            i()
        }, loadScript: function (i, o, a) {
            function s() {
                r[i] = !0, o()
            }

            var l, u;
            if (r[i])return o(), t;
            if (o = o || e, a = a || e, u = document.createElement("script"), u.type = "text/javascript", "object" == typeof i)for (l in i)u.setAttribute(l, i[l]); else u.src = i;
            "onload"in u ? (u.onload = s, u.onerror = a) : (u.onreadystatechange = function () {
                var e = u.readyState;
                ("complete" == e || "loaded" == e) && s()
            }, u.onerror = a), n(u)
        }, loadCss: function (a, s, l) {
            function u() {
                r[a] = !0, s()
            }

            function c() {
                var e = navigator.userAgent.match(/WebKit\/(\d*)/);
                return!!(e && 536 > e[1])
            }

            function d() {
                for (var e = p.styleSheets, n, i = e.length, r; i--;)if (n = e[i], r = n.ownerNode ? n.ownerNode : n.owningElement, r && r.id === h.id)return u(), t;
                (new Date).getTime() - g < 1e3 * o.maxLoadTime ? window.setTimeout(d, 0) : l()
            }

            function f() {
                try {
                    var e = m.sheet.cssRules;
                    return u(), e
                } catch (t) {
                }
                (new Date).getTime() - g < 1e3 * o.maxLoadTime ? window.setTimeout(f, 0) : l()
            }

            var p = document, h, m, g;
            if (r[a])return s(), t;
            if (s = s || e, l = l || e, h = p.createElement("link"), h.rel = "stylesheet", h.type = "text/css", h.href = a, h.id = "u" + i++, g = (new Date).getTime(), "onload"in h && !c())h.onload = u, h.onerror = l; else {
                if (navigator.userAgent.indexOf("Firefox") > 0)return m = p.createElement("style"), m.textContent = '@import "' + a + '"', f(), n(m), t;
                d()
            }
            n(h)
        }};
        return o
    }), i(Jt, [], function () {
        function e(e) {
            return e !== t
        }

        function n(e) {
            return"string" == typeof e
        }

        function i(e) {
            var t, n, i;
            for (i = h.createElement("div"), t = h.createDocumentFragment(), i.innerHTML = e; n = i.firstChild;)t.appendChild(n);
            return t
        }

        function r(e, t, n) {
            var o;
            if ("string" == typeof t)t = i(t); else if (t.length) {
                for (o = 0; t.length > o; o++)r(e, t[o], n);
                return e
            }
            for (o = e.length; o--;)n.call(e[o], t.parentNode ? t : t);
            return e
        }

        function o(e, t) {
            return e && t && -1 !== (" " + e.className + " ").indexOf(" " + t + " ")
        }

        function a(e, t) {
            var n;
            for (e = e || [], "string" == typeof e && (e = e.split(" ")), t = t || {}, n = e.length; n--;)t[e[n]] = {};
            return t
        }

        function s(e, t) {
            return new s.fn.init(e, t)
        }

        function l(e) {
            var t = arguments, n, i, r;
            for (i = 1; t.length > i; i++) {
                n = t[i];
                for (r in n)e[r] = n[r]
            }
            return e
        }

        function u(e) {
            var t = [], n, i;
            for (n = 0, i = e.length; i > n; n++)t[n] = e[n];
            return t
        }

        function c(e, t) {
            var n;
            if (e.indexOf)return e.indexOf(t);
            for (n = e.length; n--;)if (e[n] === t)return n;
            return-1
        }

        function d(e, t) {
            var n, i, r, o, a;
            if (e)if (n = e.length, n === o) {
                for (i in e)if (e.hasOwnProperty(i) && (a = e[i], t.call(a, i, a) === !1))break
            } else for (r = 0; n > r && (a = e[r], t.call(a, i, a) !== !1); r++);
            return e
        }

        function f(e, n, i) {
            for (var r = [], o = e[n]; o && 9 !== o.nodeType && (i === t || 1 !== o.nodeType || !s(o).is(i));)1 === o.nodeType && r.push(o), o = o[n];
            return r
        }

        function p(e, t, n, i) {
            for (var r = []; e; e = e[n])i && e.nodeType !== i || e === t || r.push(e);
            return r
        }

        var h = document, m = Array.prototype.push, g = Array.prototype.slice, v = /^(?:[^#<]*(<[\w\W]+>)[^>]*$|#([\w\-]*)$)/, x = a("fillOpacity fontWeight lineHeight opacity orphans widows zIndex zoom"), y = Array.isArray || function (e) {
            return"[object Array]" === Object.prototype.toString.call(e)
        }, b = /^\s*|\s*$/g, w = function (e) {
            return e.replace(b, "")
        };
        return s.fn = s.prototype = {constructor: s, selector: "", length: 0, init: function (e, t) {
            var r = this, o, a;
            if (!e)return r;
            if (e.nodeType)return r.context = r[0] = e, r.length = 1, r;
            if (n(e)) {
                if (o = "<" === e.charAt(0) && ">" === e.charAt(e.length - 1) && e.length >= 3 ? [null, e, null] : v.exec(e), o[1])for (a = i(e).firstChild; a;)this.add(a), a = a.nextSibling; else {
                    if (a = h.getElementById(o[2]), a.id !== o[2])return r.find(e);
                    r.length = 1, r[0] = a
                }
                r.context = t || h, r.selector = e
            } else this.add(e);
            return r
        }, toArray: function () {
            return u(this)
        }, add: function (e) {
            var t = this;
            return y(e) ? m.apply(t, e) : e instanceof s ? t.add(e.toArray()) : m.call(t, e), t
        }, attr: function (n, i) {
            var r = this;
            if ("object" == typeof n)d(n, function (e, t) {
                r.attr(t, e)
            }); else {
                if (!e(i))return r[0] && 1 === r[0].nodeType ? r[0].getAttribute(n) : t;
                this.each(function () {
                    1 === this.nodeType && this.setAttribute(n, i)
                })
            }
            return r
        }, css: function (n, i) {
            var r = this;
            if ("object" == typeof n)d(n, function (e, t) {
                r.css(t, e)
            }); else {
                if (n = n.replace(/-(\D)/g, function (e, t) {
                    return t.toUpperCase()
                }), !e(i))return r[0] ? r[0].style[n] : t;
                "number" != typeof i || x[n] || (i += "px"), r.each(function () {
                    var e = this.style;
                    "opacity" === n && this.runtimeStyle && this.runtimeStyle.opacity === t && (e.filter = "" === i ? "" : "alpha(opacity=" + 100 * i + ")");
                    try {
                        e[n] = i
                    } catch (r) {
                    }
                })
            }
            return r
        }, remove: function () {
            for (var e = this, t, n = this.length; n--;)t = e[n], t.parentNode && t.parentNode.removeChild(t);
            return this
        }, empty: function () {
            for (var e = this, t, n = this.length; n--;)for (t = e[n]; t.firstChild;)t.removeChild(t.firstChild);
            return this
        }, html: function (t) {
            var n = this, i;
            if (e(t)) {
                for (i = n.length; i--;)n[i].innerHTML = t;
                return n
            }
            return n[0] ? n[0].innerHTML : ""
        }, text: function (t) {
            var n = this, i;
            if (e(t)) {
                for (i = n.length; i--;)n[i].innerText = n[0].textContent = t;
                return n
            }
            return n[0] ? n[0].innerText || n[0].textContent : ""
        }, append: function () {
            return r(this, arguments, function (e) {
                1 === this.nodeType && this.appendChild(e)
            })
        }, prepend: function () {
            return r(this, arguments, function (e) {
                1 === this.nodeType && this.insertBefore(e, this.firstChild)
            })
        }, before: function () {
            var e = this;
            return e[0] && e[0].parentNode ? r(e, arguments, function (e) {
                this.parentNode.insertBefore(e, this.nextSibling)
            }) : e
        }, after: function () {
            var e = this;
            return e[0] && e[0].parentNode ? r(e, arguments, function (e) {
                this.parentNode.insertBefore(e, this)
            }) : e
        }, appendTo: function (e) {
            return s(e).append(this), this
        }, addClass: function (e) {
            return this.toggleClass(e, !0)
        }, removeClass: function (e) {
            return this.toggleClass(e, !1)
        }, toggleClass: function (e, t) {
            var n = this;
            return-1 !== e.indexOf(" ") ? d(e.split(" "), function () {
                n.toggleClass(this, t)
            }) : n.each(function () {
                var n = this, i;
                o(n, e) !== t && (i = n.className, t ? n.className += i ? " " + e : e : n.className = w((" " + i + " ").replace(" " + e + " ", " ")))
            }), n
        }, hasClass: function (e) {
            return o(this[0], e)
        }, each: function (e) {
            return d(this, e)
        }, show: function () {
            return this.css("display", "")
        }, hide: function () {
            return this.css("display", "none")
        }, slice: function () {
            return new s(g.apply(this, arguments))
        }, eq: function (e) {
            return-1 === e ? this.slice(e) : this.slice(e, +e + 1)
        }, first: function () {
            return this.eq(0)
        }, last: function () {
            return this.eq(-1)
        }, replaceWith: function (e) {
            var t = this;
            return t[0] && t[0].parentNode.replaceChild(s(e)[0], t[0]), t
        }, wrap: function (e) {
            return e = s(e)[0], this.each(function () {
                var t = this, n = e.cloneNode(!1);
                t.parentNode.insertBefore(n, t), n.appendChild(t)
            })
        }, unwrap: function () {
            return this.each(function () {
                for (var e = this, t = e.firstChild, n; t;)n = t, t = t.nextSibling, e.parentNode.insertBefore(n, e)
            })
        }, clone: function () {
            var e = [];
            return this.each(function () {
                e.push(this.cloneNode(!0))
            }), s(e)
        }, push: m, sort: [].sort, splice: [].splice}, l(s, {extend: l, toArray: u, inArray: c, isArray: y, each: d, trim: w, makeMap: a}), d({parent: function (e) {
            var t = e.parentNode;
            return t && 11 !== t.nodeType ? t : null
        }, parents: function (e) {
            return f(e, "parentNode")
        }, parentsUntil: function (e, t) {
            return f(e, "parentNode", t)
        }, next: function (e) {
            return p(e, "nextSibling", 1)
        }, prev: function (e) {
            return p(e, "previousSibling", 1)
        }, nextNodes: function (e) {
            return p(e, "nextSibling")
        }, prevNodes: function (e) {
            return p(e, "previousSibling")
        }, children: function (e) {
            return p(e.firstChild, "nextSibling", 1)
        }, contents: function (e) {
            return u(("iframe" === e.nodeName ? e.contentDocument || e.contentWindow.document : e).childNodes)
        }}, function (e, t) {
            s.fn[e] = function (n) {
                var i = this, r;
                if (i.length > 1)throw Error("MinQuery only supports traverse functions on a single node.");
                return i[0] && (r = t(i[0], n)), r = s(r), n && "parentsUntil" !== e ? r.filter(n) : r
            }
        }), s.fn.filter = function () {
            return this
        }, s.fn.is = function () {
            return!0
        }, s.fn.init.prototype = s.fn, s
    }), i(Kt, [], function () {
        function e() {
            return!1
        }

        function t() {
            return!0
        }

        var n = "__bindings";
        return{fire: function (i, r, o) {
            var a = this, s, l, u, c, d;
            if (i = i.toLowerCase(), r = r || {}, r.type = i, r.target || (r.target = a), r.preventDefault || (r.preventDefault = function () {
                r.isDefaultPrevented = t
            }, r.stopPropagation = function () {
                r.isPropagationStopped = t
            }, r.stopImmediatePropagation = function () {
                r.isImmediatePropagationStopped = t
            }, r.isDefaultPrevented = e, r.isPropagationStopped = e, r.isImmediatePropagationStopped = e), a[n] && (s = a[n][i]))for (l = 0, u = s.length; u > l && (s[l] = c = s[l], !r.isImmediatePropagationStopped()); l++)if (c.call(a, r) === !1)return r.preventDefault(), r;
            if (o !== !1 && a.parent)for (d = a.parent(); d && !r.isPropagationStopped();)d.fire(i, r, !1), d = d.parent();
            return r
        }, on: function (e, t) {
            var i = this, r, o, a, s;
            if (t === !1 && (t = function () {
                return!1
            }), t)for (a = e.toLowerCase().split(" "), s = a.length; s--;)e = a[s], r = i[n], r || (r = i[n] = {}), o = r[e], o || (o = r[e] = [], i.addRemoveNativeEvent && i.addRemoveNativeEvent(e, !0)), o.push(t);
            return i
        }, off: function (e, t) {
            var i = this, r, o = i[n], a, s, l, u;
            if (o)if (e)for (l = e.toLowerCase().split(" "), r = l.length; r--;) {
                if (e = l[r], a = o[e], !e) {
                    for (s in o)i.addRemoveNativeEvent && i.addRemoveNativeEvent(s, !1), delete o[s];
                    return i
                }
                if (a) {
                    if (t)for (u = a.length; u--;)a[u] === t && a.splice(u, 1); else a.length = 0;
                    !a.length && i.addRemoveNativeEvent && (i.addRemoveNativeEvent(e, !1), delete o[e])
                }
            } else {
                if (i.unbindNative)for (e in o)i.unbindNative(e);
                i[n] = []
            }
            return i
        }}
    }), i(Zt, [l], function (e) {
        var n = e.each, i = {"delete": 46};
        return function (e) {
            var r = this, o = {};
            e.on("keyup keypress keydown", function (e) {
                /INPUT|TEXTAREA/.test(e.target.nodeName) || n(o, function (n) {
                    var i = e.metaKey || e.ctrlKey;
                    if (n.ctrl == i && n.alt == e.altKey && n.shift == e.shiftKey)return e.keyCode == n.keyCode || e.charCode && e.charCode == n.charCode ? (e.preventDefault(), "keydown" == e.type && n.func(), !0) : t
                })
            }), r.add = function (e, t) {
                n(e.toLowerCase().split(" "), function (e) {
                    var r = {func: t, alt: !1, ctrl: !1, shift: !1};
                    n(e.split("+"), function (e) {
                        switch (e) {
                            case"alt":
                            case"ctrl":
                            case"shift":
                                r[e] = !0;
                                break;
                            default:
                                r.charCode = e.charCodeAt(0), r.keyCode = i[e] || e.toUpperCase().charCodeAt(0)
                        }
                    }), o[(r.ctrl ? "ctrl" : "") + "," + (r.alt ? "alt" : "") + "," + (r.shift ? "shift" : "") + "," + r.keyCode] = r
                })
            }
        }
    }), i(Qt, [l], function (e) {
        function n(e) {
            return null === e || e === t ? "" : ("" + e).replace(/^\s*|\s*$/g, "")
        }

        function i(e, o) {
            var a = this, s, l;
            return e = n(e), o = a.settings = o || {}, /^([\w\-]+):([^\/]{2})/i.test(e) || /^\s*#/.test(e) ? (a.source = e, t) : (0 === e.indexOf("/") && 0 !== e.indexOf("//") && (e = (o.base_uri ? o.base_uri.protocol || "http" : "http") + "://mce_host" + e), /^[\w\-]*:?\/\//.test(e) || (l = o.base_uri ? o.base_uri.path : new i(location.href).directory, e = (o.base_uri && o.base_uri.protocol || "http") + "://mce_host" + a.toAbsPath(l, e)), e = e.replace(/@@/g, "(mce_at)"), e = /^(?:(?![^:@]+:[^:@\/]*@)([^:\/?#.]+):)?(?:\/\/)?((?:(([^:@\/]*):?([^:@\/]*))?@)?([^:\/?#]*)(?::(\d*))?)(((\/(?:[^?#](?![^?#\/]*\.[^?#\/.]+(?:[?#]|$)))*\/?)?([^?#\/]*))(?:\?([^#]*))?(?:#(.*))?)/.exec(e), r(["source", "protocol", "authority", "userInfo", "user", "password", "host", "port", "relative", "path", "directory", "file", "query", "anchor"], function (t, n) {
                var i = e[n];
                i && (i = i.replace(/\(mce_at\)/g, "@@")), a[t] = i
            }), s = o.base_uri, s && (a.protocol || (a.protocol = s.protocol), a.userInfo || (a.userInfo = s.userInfo), a.port || "mce_host" !== a.host || (a.port = s.port), a.host && "mce_host" !== a.host || (a.host = s.host), a.source = ""), t)
        }

        var r = e.each;
        return i.prototype = {setPath: function (e) {
            var t = this;
            e = /^(.*?)\/?(\w+)?$/.exec(e), t.path = e[0], t.directory = e[1], t.file = e[2], t.source = "", t.getURI()
        }, toRelative: function (e) {
            var t = this, n;
            if ("./" === e)return e;
            if (e = new i(e, {base_uri: t}), "mce_host" != e.host && t.host != e.host && e.host || t.port != e.port || t.protocol != e.protocol)return e.getURI();
            var r = t.getURI(), o = e.getURI();
            return r == o || "/" == r.charAt(r.length - 1) && r.substr(0, r.length - 1) == o ? r : (n = t.toRelPath(t.path, e.path), e.query && (n += "?" + e.query), e.anchor && (n += "#" + e.anchor), n)
        }, toAbsolute: function (e, t) {
            return e = new i(e, {base_uri: this}), e.getURI(this.host == e.host && this.protocol == e.protocol ? t : 0)
        }, toRelPath: function (e, t) {
            var n, i = 0, r = "", o, a;
            if (e = e.substring(0, e.lastIndexOf("/")), e = e.split("/"), n = t.split("/"), e.length >= n.length)for (o = 0, a = e.length; a > o; o++)if (o >= n.length || e[o] != n[o]) {
                i = o + 1;
                break
            }
            if (e.length < n.length)for (o = 0, a = n.length; a > o; o++)if (o >= e.length || e[o] != n[o]) {
                i = o + 1;
                break
            }
            if (1 === i)return t;
            for (o = 0, a = e.length - (i - 1); a > o; o++)r += "../";
            for (o = i - 1, a = n.length; a > o; o++)r += o != i - 1 ? "/" + n[o] : n[o];
            return r
        }, toAbsPath: function (e, t) {
            var n, i = 0, o = [], a, s;
            for (a = /\/$/.test(t) ? "/" : "", e = e.split("/"), t = t.split("/"), r(e, function (e) {
                e && o.push(e)
            }), e = o, n = t.length - 1, o = []; n >= 0; n--)0 !== t[n].length && "." !== t[n] && (".." !== t[n] ? i > 0 ? i-- : o.push(t[n]) : i++);
            return n = e.length - i, s = 0 >= n ? o.reverse().join("/") : e.slice(0, n).join("/") + "/" + o.reverse().join("/"), 0 !== s.indexOf("/") && (s = "/" + s), a && s.lastIndexOf("/") !== s.length - 1 && (s += a), s
        }, getURI: function (e) {
            var t, n = this;
            return(!n.source || e) && (t = "", e || (n.protocol && (t += n.protocol + "://"), n.userInfo && (t += n.userInfo + "@"), n.host && (t += n.host), n.port && (t += ":" + n.port)), n.path && (t += n.path), n.query && (t += "?" + n.query), n.anchor && (t += "#" + n.anchor), n.source = t), n.source
        }}, i.parseURI = function (t, n) {
            return n = n || {}, new i(t, e.extend({base_uri: new i(n.base_url || document.location.href.split("?")[0].replace(/\/[^\/]+$/, "") + "/")}, n))
        }, i
    }), i(en, [], function () {
        function e(e, n, i, r) {
            return t(e).then(n, i, r)
        }

        function t(e) {
            var t, n;
            return e instanceof i ? t = e : s(e) ? (n = a(), e.then(function (e) {
                n.resolve(e)
            }, function (e) {
                n.reject(e)
            }, function (e) {
                n.progress(e)
            }), t = n.promise) : t = r(e), t
        }

        function n(t) {
            return e(t, o)
        }

        function i(e) {
            this.then = e
        }

        function r(e) {
            var n = new i(function (n) {
                try {
                    return t("function" == typeof n ? n(e) : e)
                } catch (i) {
                    return o(i)
                }
            });
            return n
        }

        function o(e) {
            var n = new i(function (n, i) {
                try {
                    return t("function" == typeof i ? i(e) : o(e))
                } catch (r) {
                    return o(r)
                }
            });
            return n
        }

        function a() {
            function e(e, t, n) {
                return f(e, t, n)
            }

            function n(e) {
                return h(t(e))
            }

            function r(e) {
                return h(o(e))
            }

            function s(e) {
                return p(e)
            }

            var l, u, c, d, f, p, h;
            return u = new i(e), l = {then: e, resolve: n, reject: r, progress: s, promise: u, resolver: {resolve: n, reject: r, progress: s}}, c = [], d = [], f = function (e, t, n) {
                var i, r;
                return i = a(), r = "function" == typeof n ? function (e) {
                    try {
                        i.progress(n(e))
                    } catch (t) {
                        i.progress(t)
                    }
                } : function (e) {
                    i.progress(e)
                }, c.push(function (n) {
                    n.then(e, t).then(i.resolve, i.reject, r)
                }), d.push(r), i.promise
            }, p = function (e) {
                return m(d, e), e
            }, h = function (e) {
                return f = e.then, h = t, p = x, m(c, e), d = c = w, e
            }, l
        }

        function s(e) {
            return e && "function" == typeof e.then
        }

        function l(t, n, i, r, o) {
            return g(2, arguments), e(t, function (t) {
                function s(e) {
                    m(e)
                }

                function l(e) {
                    h(e)
                }

                var u, c, d, f, p, h, m, g, x, y;
                if (x = t.length >>> 0, u = Math.max(0, Math.min(n, x)), d = [], c = x - u + 1, f = [], p = a(), u)for (g = p.progress, m = function (e) {
                    f.push(e), --c || (h = m = v, p.reject(f))
                }, h = function (e) {
                    d.push(e), --u || (h = m = v, p.resolve(d))
                }, y = 0; x > y; ++y)y in t && e(t[y], l, s, g); else p.resolve(d);
                return p.promise.then(i, r, o)
            })
        }

        function u(e, t, n, i) {
            function r(e) {
                return t ? t(e[0]) : e[0]
            }

            return l(e, 1, r, n, i)
        }

        function c(e, t, n, i) {
            return g(1, arguments), f(e, x).then(t, n, i)
        }

        function d() {
            return f(arguments, x)
        }

        function f(t, n) {
            return e(t, function (t) {
                var i, r, o, s, l, u;
                if (o = r = t.length >>> 0, i = [], u = a(), o)for (s = function c(t, r) {
                    e(t, n).then(function (e) {
                        i[r] = e, --o || u.resolve(i)
                    }, u.reject)
                }, l = 0; r > l; l++)l in t ? s(t[l], l) : --o; else u.resolve(i);
                return u.promise
            })
        }

        function p(t, n) {
            var i = b.call(arguments, 1);
            return e(t, function (t) {
                var r;
                return r = t.length, i[0] = function (t, i, o) {
                    return e(t, function (t) {
                        return e(i, function (e) {
                            return n(t, e, o, r)
                        })
                    })
                }, y.apply(t, i)
            })
        }

        function h(t, n, i) {
            var r = arguments.length > 2;
            return e(t, function (e) {
                return e = r ? i : e, n.resolve(e), e
            }, function (e) {
                return n.reject(e), o(e)
            }, n.progress)
        }

        function m(e, t) {
            for (var n, i = 0; n = e[i++];)n(t)
        }

        function g(e, t) {
            for (var n, i = t.length; i > e;)if (n = t[--i], n && "function" != typeof n)throw Error("arg " + i + " must be a function")
        }

        function v() {
        }

        function x(e) {
            return e
        }

        var y, b, w;
        return e = {}, e.defer = a, e.resolve = t, e.reject = n, e.join = d, e.all = c, e.map = f, e.reduce = p, e.any = u, e.some = l, e.chain = h, e.isPromise = s, i.prototype = {always: function (e, t) {
            return this.then(e, e, t)
        }, otherwise: function (e) {
            return this.then(w, e)
        }, yield: function (e) {
            return this.then(function () {
                return e
            })
        }, spread: function (e) {
            return this.then(function (t) {
                return c(t, function (t) {
                    return e.apply(w, t)
                })
            })
        }}, b = [].slice, y = [].reduce || function (e) {
            var t, n, i, r, o;
            if (o = 0, t = Object(this), r = t.length >>> 0, n = arguments, 1 >= n.length)for (; ;) {
                if (o in t) {
                    i = t[o++];
                    break
                }
                if (++o >= r)throw new TypeError
            } else i = n[1];
            for (; r > o; ++o)o in t && (i = e(i, t[o], o, t));
            return i
        }, e
    }),i(tn, [l, K, Z], function (e, t, n) {
        function i(e) {
            e && this.add(e)
        }

        function r(e, t) {
            return function (n) {
                i.FileSystemManager.convertResult(n, function (n) {
                    t ? e({from: t, to: n}) : e({files: n})
                })
            }
        }

        var o = Array.prototype.push, a = Array.prototype.slice;
        return i.prototype = {length: 0, add: function (t) {
            var n = this;
            return e.isArray(t) ? o.apply(n, t) : t instanceof i ? n.add(t.toArray()) : o.call(n, t), n
        }, slice: function () {
            return new i(a.apply(this, arguments))
        }, eq: function (e) {
            return-1 === e ? this.slice(e) : this.slice(e, +e + 1)
        }, each: function (t) {
            return e.each(this, t), this
        }, toArray: function () {
            return e.toArray(this)
        }, indexOf: function (t) {
            return e.inArray(t, this)
        }, toPathArray: function () {
            var e = [];
            return this.each(function (t) {
                var n = t.path;
                t.info && t.info.link && (n = t.info.link), e.push(n)
            }), e
        }, moveTo: function (e, n, i) {
            t.exec("moveTo", {from: this.toPathArray(), to: e}, r.call(this, n, this), i)
        }, copyTo: function (e, n, i) {
            t.exec("copyTo", {from: this.toPathArray(), to: e}, r.call(this, n, this), i)
        }, remove: function (e, n) {
            t.exec("delete", {paths: this.toPathArray()}, r.call(this, e), n)
        }, download: function (e) {
            var i = this, r = [], o;
            e = e || "files.zip", i.each(function (e) {
                o = n.dirname(e.path), r.push(e.name)
            }), document.location.href = t.url + "?act=download&path=" + o + "&names=" + r.join(",") + "&zipname=" + e
        }, zip: function (e, r, o) {
            var a = this, s, l = [];
            a.each(function (e) {
                s = n.dirname(e.path), l.push(e.name)
            }), t.exec("zip", {to: e, path: s, names: l}, function (e) {
                i.FileSystemManager.convertResult(e, function (e) {
                    r({to: e})
                })
            }, o)
        }, populateMetaData: function (e, i, r) {
            var o = this;
            t.exec("FileInfo", {paths: o.toPathArray(), meta: !0, insert: e.insert}, function (e) {
                var t, r = {};
                for (t = e.length; t--;)r[e[t].path] = e[t].meta;
                o.each(function (e) {
                    if (!e.meta) {
                        var t = e.path;
                        e.info && e.info.link && (t = e.info.link, e.path = e.info.link, e.parentPath = n.dirname(e.path)), e.meta = r[t]
                    }
                }), i(o)
            }, function () {
                r(o)
            })
        }}, i
    }),i(nn, [Z, tn, K], function (e, n, i) {
        function r(e, t) {
            if (e) {
                var n = new a(e.path, t);
                return n.size = e.size, n.lastModified = new Date(1e3 * e.lastModified), n.isFile = e.isFile, n.isDirectory = !e.isFile, n.canRead = e.canRead, n.canWrite = e.canWrite, n.canRename = e.canRename, n.canEdit = e.canEdit, n.canView = e.canView, n.canPreview = e.canPreview, n.exists = e.exists, n.meta = e.meta, n.info = e.info, n
            }
        }

        function o(e, t) {
            return function (i) {
                n.FileSystemManager.convertResult(i, function (n) {
                    t ? e({from: t, to: n}) : e({files: n})
                })
            }
        }

        function a(r, s) {
            var l = this;
            l.path = r, l.fileSystem = s, l.name = e.basename(r), this.parentPath = e.dirname(r), l.isFile = !1, l.isDirectory = !1, l.canRead = !1, l.canWrite = !1, l.canRename = !1, l.canEdit = !1, l.canPreview = !1, l.exists = !1, l.size = 0, l.lastModified = new Date, l.getParentFile = function (e, t) {
                s.getFile(this.parentPath, e, t)
            }, l.copyTo = function (t, n, a, s) {
                var u = e.join(t, n);
                i.exec("copyTo", {from: r, to: u}, o(a, l), s)
            }, l.moveTo = function (t, n, a, s) {
                var u = e.join(t, n);
                i.exec("moveTo", {from: r, to: u}, o(a, l), s)
            }, l.unzip = function (e, t, n) {
                i.exec("unzip", {from: this.path, to: e}, o(t, l), n)
            }, l.remove = function (e, t) {
                i.exec("delete", {paths: [r]}, o(e), t)
            }, l.download = function () {
                document.location.href = i.url + "?act=download&path=" + r
            }, l.listFiles = function (e, t, i) {
                e.path = e.path || this.path, n.FileSystemManager.listFiles(e, t, i)
            }, l.putFileContents = function (e, t, n) {
                i.exec("putFileContents", {path: this.path, content: e}, function (e) {
                    t(a.resultToFile(e))
                }, n)
            }, l.getFileContents = function (e, t) {
                i.exec("getFileContents", {path: this.path}, e, t)
            }, l.getMetaData = function (e, n) {
                var r = this;
                return r.meta ? (e(r.meta), t) : (i.exec("FileInfo", {path: r.path, meta: !0}, function (t) {
                    r.meta = t.meta, e(t.meta)
                }, n), t)
            }, l.getConfig = function (e, n) {
                var r = this;
                return r.config ? (e(r.config), t) : (i.exec("getConfig", {path: r.path}, function (t) {
                    r.config = t, e(t)
                }, n), t)
            }
        }

        return a.resultToFile = r, a
    }),i(rn, [Z], function (e) {
        function t(t, n, i, r) {
            var o = this;
            o.rootName = e.basename(n), o.rootPath = n, o.meta = i, o.config = r, o.getRootFile = function (e, n) {
                t.getFile(o.rootPath, e, n)
            }, o.getFile = function (e, n, i) {
                t.getFile(e, n, i)
            }
        }

        return t
    }),i(on, [rn, nn, tn, Z, K], function (e, n, i, r, o) {
        function a(e, t) {
            var n, i;
            for (n = e.length; n--;)if (i = e[n], t === i.rootPath || 0 === t.indexOf(i.rootPath + "/"))return i
        }

        var s, l = {}, u = {getFileSystems: function (n, i) {
            return s ? (n(s), t) : (o.exec("listRoots", [], function (t) {
                var i, r = [];
                for (i = 0; t.length > i; i++)r.push(new e(u, t[i].path, t[i].meta, t[i].config));
                s = r, n(s)
            }, i), t)
        }, createDocument: function (e, t, n, i) {
            o.exec("createDocument", {path: e, name: t.name, fields: t.fields, template: t.template}, function (e) {
                u.convertResult(e, n)
            }, i)
        }, createDirectory: function (e, t, n, i) {
            o.exec("createDirectory", {path: e, template: t.template, overwrite: t.overwrite}, function (e) {
                u.convertResult(e, n)
            }, i)
        }, getFile: function (e, t, n) {
            "string" == typeof e && (e = {path: e}), o.exec("FileInfo", e, function (e) {
                u.convertResult(e, t)
            }, n)
        }, getFiles: function (e, t, n) {
            var r, a, s = [];
            for (r = 0; e.length > r; r++)a = l[e[r]], a && (s.push(a), e.splice(r, 1));
            e.length ? o.exec("FileInfo", {paths: e}, function (e) {
                u.convertResult(e, function (e) {
                    t(new i([].concat(s, e.toArray())), u)
                })
            }, n) : t(new i(s))
        }, listFiles: function (e, t, s) {
            this.getFileSystems(function (l) {
                function u(e) {
                    var t, n, i = e.columns, r = e.data, o, a = [];
                    for (t = 0; r.length > t; t++) {
                        for (o = {}, n = 0; i.length > n; n++)o[i[n]] = r[t][n];
                        a.push(o)
                    }
                    return a
                }

                o.exec("listFiles", e, function (o) {
                    var s, c, d, f, p = [], h, m, g;
                    for (h = u(o), m = o.file ? o.file.path : e.path, g = a(l, m), s = 0; h.length > s; s++)c = h[s], c.info && c.info.link && (m = c.info.link), m = o.file ? r.join(o.file.path, c.name) : r.join(e.path, c.name), d = c.attrs, f = new n(m, g), f.size = c.size, f.lastModified = new Date(1e3 * c.modified), f.isFile = "d" !== d[0], f.isDirectory = !f.isFile, f.canRead = "-" != d[1], f.canWrite = "-" != d[2], f.canRename = "-" != d[3], f.canEdit = "-" != d[4], f.canView = "-" != d[5], f.canPreview = "-" != d[6], f.info = c.info, f.exists = !0, p.push(f);
                    t({file: n.resultToFile(o.file, g), urlFile: n.resultToFile(o.urlFile, g), config: o.config, files: new i(p)})
                }, s)
            })
        }, convertResult: function (e, t) {
            this.getFileSystems(function (r) {
                var o, s, u;
                if (e instanceof Array) {
                    for (u = [], o = 0; e.length > o; o++)s = n.resultToFile(e[o], a(r, e[o].path)), l[s.path] = s, u.push(s);
                    t(new i(u))
                } else s = n.resultToFile(e, a(r, e.path)), l[s.path] = s, t(s)
            })
        }};
        return i.FileSystemManager = u, u
    }),i(an, [jt, _, K, Gt], function (e, t, n, i) {
        return e.extend({render: function (e) {
            t.create({type: "window", title: "Login", items: [
                {type: "form", items: [
                    {label: "Username", name: "username", type: "textbox", minWidth: 250},
                    {label: "Password", name: "password", type: "textbox", subtype: "password", minWidth: 250},
                    {label: " ", name: "persistent", type: "checkbox", checked: !0, text: "Keep me logged in"},
                    {type: "label", name: "msg", text: i.translate("Wrong username/password"), hidden: !0}
                ]}
            ], onsubmit: function (t) {
                var i = this, r = t.data;
                t.preventDefault(), n.exec("login", {username: r.username, password: r.password, persistent: r.persistent}, function (t) {
                    t ? (e.login = !0, e.onlogin && e.onlogin(i)) : i.find("#msg").eq(0).show(!0).parent().reflow()
                })
            }, onclose: function () {
                e.oncancel && !e.login && e.oncancel()
            }, buttons: [
                {text: "Login", subtype: "primary", onclick: "submit"},
                {text: "Cancel", onclick: "close"}
            ]}).renderTo(document.body).reflow()
        }})
    }),i(sn, [jt, _, K], function (e, t, n) {
        return e.extend({render: function (e) {
            function i() {
                r.find("#basicauth_details")[0].visible("basic" == r.find("#authenticator").value()).parent().reflow(), r.find("#sessionauth_details")[0].visible("basic" != r.find("#authenticator").value()).parent().reflow()
            }

            var r;
            r = t.create({type: "window", title: "Installation", items: [
                {type: "form", items: [
                    {type: "label", text: "This will configure the basic settings needed for the MoxieManager."},
                    {label: "License key", name: "license", type: "textbox", placeholder: "xxxx-xxxx-xxxx-xxxx-xxxx-xxxx-xxxx-xxxx", tooltip: "Paste you license key you got from the www.moxiemanager.com website", minWidth: 250},
                    {label: "Authenticator", name: "authenticator", onselect: i, type: "listbox", values: [
                        {text: "Basic authenticator", value: "basic", selected: !0},
                        {text: "Session authenticator", value: "session"}
                    ]},
                    {type: "fieldset", name: "basicauth_details", title: "Basic authenticator", items: [
                        {type: "label", maxWidth: 400, multiline: !0, text: "Enter the username/password you want to use to login to the MoxieManager"},
                        {label: "User name", name: "username", type: "textbox"},
                        {label: "Password", name: "password", type: "textbox", subtype: "password"}
                    ]},
                    {type: "fieldset", hidden: !0, name: "sessionauth_details", title: "Session authenticator", items: [
                        {type: "label", maxWidth: 400, multiline: !0, text: "Enter the session key to authorize the user by."},
                        {label: "Session name", name: "logged_in_key", type: "textbox", value: "isLoggedIn", tooltip: "Session name to check"}
                    ]}
                ]}
            ], onsubmit: function (i) {
                i.preventDefault(), n.exec("install", i.data, function (n) {
                    r.close(), n === !0 ? e.oninstall() : t.create({type: "window", title: "Couldn't write config file", padding: 10, spacing: 5, direction: "column", items: [
                        {type: "label", text: "You need to manually paste this into your config file located in the root of moxiemanager."},
                        {type: "textbox", multiline: !0, value: n, minWidth: 700, minHeight: 500, spellcheck: !1, onpostrender: function (e) {
                            e.control.getEl().select()
                        }, onclick: function (e) {
                            e.target.select()
                        }}
                    ], buttons: [
                        {text: "Close", onclick: "close"}
                    ]}).renderTo(document.body).reflow()
                })
            }, buttons: [
                {text: "Install", subtype: "primary", onclick: "submit"},
                {text: "Cancel", onclick: "close"}
            ]}).renderTo(document.body).reflow()
        }})
    }),i(ln, [jt, l, on, At, an, sn, K], function (e, n, i, r, o, a, s) {
        var l = e.extend({render: function (e) {
            function u() {
                i.getFileSystems(function (t) {
                    var i = t[0].config;
                    c.renderAsDialog(n.extend({disabled_tools: i["general.disabled_tools"], hidden_tools: i["general.hidden_tools"]}, e))
                })
            }

            var c = this;
            s.errorHandler = s.errorHandler || function (e) {
                if (l.hideThrobber && l.hideThrobber(), 11 == e.code) {
                    var n = new a;
                    return n.render({oninstall: function () {
                        u()
                    }}), t
                }
                if (10 == e.code) {
                    if (e.data && e.data.login_url)return document.location.href = e.data.login_url, t;
                    var i = new o;
                    return i.render({onlogin: function (e) {
                        e.close(), u()
                    }}), t
                }
                r.alert({title: "Error", text: e.message + "\n" + (e.data ? e.data : "")})
            }, u()
        }});
        return l
    }),i(un, [ln, _, on, Z], function (e, t, n, i) {
        return e.extend({renderAsDialog: function (e) {
            t.create({type: "window", title: "Create new folder", items: [
                {type: "form", items: [
                    {label: "Name", name: "name", type: "textbox", minWidth: 300}
                ]}
            ], buttons: [
                {text: "Create", subtype: "primary", onclick: "submit"},
                {text: "Cancel", onclick: "close"}
            ], onsubmit: function (t) {
                e.onbeforecreate && e.onbeforecreate(), n.createDirectory(i.join(e.path, t.data.name), {template: e.template}, function (t) {
                    e.oncreate({file: t})
                })
            }}).renderTo(document.body).reflow()
        }})
    }),i(cn, [ln, _, on], function (e, t, n) {
        return e.extend({renderAsDialog: function (e) {
            function i(e, t) {
                var n, i;
                if (e)for (e = e.split(/\s*,\s*/), n = 0; e.length > n; n++)i = e[n].split(/\s*=\s*/), t(i[0], i[1] || i[0])
            }

            n.getFile(e.path, function (r) {
                r.getConfig(function (r) {
                    var o = [];
                    o.push({label: "Name", name: "name", type: "textbox", minWidth: 300}), i(r["createdoc.fields"], function (e, t) {
                        o.push({label: e, name: "field_" + t, type: "textbox", minWidth: 300})
                    }), t.create({type: "window", title: "Create new document", items: [
                        {type: "form", items: o}
                    ], buttons: [
                        {text: "Create", subtype: "primary", onclick: "submit"},
                        {text: "Cancel", onclick: "close"}
                    ], onsubmit: function (t) {
                        var i = {}, r, o = t.data;
                        for (r in o)0 === r.indexOf("field_") && (i[r.substr(6)] = o[r]);
                        e.onbeforecreate && e.onbeforecreate(), n.createDocument(e.path, {template: e.template, name: t.data.name, fields: i}, function (t) {
                            e.oncreate({file: t})
                        })
                    }}).renderTo(document.body).reflow()
                })
            })
        }})
    }),i(dn, [ln, _, l, on, Z, At, Qt], function (e, n, i, r, o, a, s) {
        function l(e, t) {
            var n = t.meta.url;
            return e.no_host = e.no_host || e.remove_script_host, e.relative_urls ? n = s.parseURI(e.document_base_url || e.default_base_url).toRelative(n) : (e.document_base_url || e.no_host) && (n = s.parseURI(e.document_base_url).toAbsolute(s.parseURI(n).getURI(!0), e.no_host)), t.url = t.meta.url = n, t
        }

        return e.extend({renderAsDialog: function (e) {
            /^(txt|html?|php)$/.test(o.extname(e.path).substr(1)) ? this.renderEditTextDialog(e) : this.renderEditImageDialog(e)
        }, renderEditTextDialog: function (e) {
            r.getFile(e.path, function (t) {
                t.getFileContents(function (i) {
                    n.create({type: "window", title: "Edit", padding: 10, items: {type: "textbox", name: "content", multiline: !0, spellcheck: !1, minWidth: 500, minHeight: 400, value: i.content}, onsubmit: function (n) {
                        e.onbeforesave && e.onbeforesave(), t.putFileContents(n.data.content, function () {
                            e.onsave && e.onsave({file: l(e, t), content: n.data.content})
                        })
                    }, buttons: [
                        {text: "Save", subtype: "primary", onclick: "submit"},
                        {text: "Cancel", onclick: "close"}
                    ]}).renderTo(document.body).reflow()
                })
            })
        }, renderEditImageDialog: function (e) {
            function s(e) {
                y.find("imagecanvas")[0].zoom(e.value)
            }

            function u(t) {
                return function () {
                    b.cancel(), y.find("form:visible slider").each(function (e) {
                        e.reset()
                    }), "crop" == t && b.crop(), "redeye" == t && b.redeye(20), ("grayscale" == t || "invert" == t || "sharpen" == t || "emboss" == t) && b.filter(t).apply(), "fliprotate" == t && b.startTransation(), "resize" == t && (_ = b.size().w, E = b.size().h, y.find("#rw").value(b.size().w), y.find("#rh").value(b.size().h)), x = t, y.find("panel form").hide(), y.find("#" + t)[0].show().parent().reflow(), "save" == t && (y.find("#saveas").value(o.basename(e.path)), y.find("#saveas")[0].focus())
                }
            }

            function c() {
                b.saveAs(y.find("#saveas").value(), function (t) {
                    r.convertResult(t, function (t) {
                        y.close(), l(e, t), e.onsave && e.onsave({file: t})
                    })
                }, function (e) {
                    a.alert({title: "Error", text: e.message + "\n" + (e.data ? e.data : "")})
                })
            }

            function d(e) {
                var t = e.control;
                y.find("#undo,#revert,#savebtn").disabled(!t.canUndo()), y.find("#redo").disabled(!t.canRedo())
            }

            function f() {
                var e = y.find("treeview")[0];
                e.find("*").each(function (e) {
                    e.selected(!1)
                }), "resize" == x && b.resize(y.find("#rw").value(), y.find("#rh").value()), b.apply(), u("main")(), w = null
            }

            function p() {
                var e = y.find("treeview")[0];
                e.find("*").each(function (e) {
                    e.selected(!1)
                }), this.parent().find("slider").exec("reset"), u("main")(), w = null
            }

            function h(e) {
                return function () {
                    var t = this;
                    t.on("dragstart", function () {
                        w = b.filter(e)
                    }), t.on("drag", function (e) {
                        w.live(e.value)
                    }), t.on("dragend", function (e) {
                        w.apply(e.value)
                    })
                }
            }

            function m() {
                var e = this.find("slider");
                e.each(function (t) {
                    t.on("dragstart", function () {
                        w = w || b.filter("colorize")
                    }), t.on("drag", function () {
                        w.live(e[0].value(), e[1].value(), e[2].value())
                    }), t.on("dragend", function () {
                        w.apply(e[0].value(), e[1].value(), e[2].value())
                    })
                })
            }

            function g(e) {
                var t, n, i, r;
                t = y.find("#rw")[0], n = y.find("#rh")[0], i = t.value(), r = n.value(), y.find("#constrain")[0].checked() && _ && E && i && r && (e.control == t ? (r = Math.round(i / _ * r), n.value(r)) : (i = Math.round(r / E * i), t.value(i))), _ = i, E = r
            }

            function v() {
                var e = this.parent().find("treeview")[0].items();
                this.hasCanvasSupport() ? this.hasWebGlSupport() || e.eq(-1)[0].find("#triangleblur,#hue,#sharpen,#emboss").hide() : (e.eq(-1).hide(), this.parent().find("#undo,#redo").hide(), this.parent().reflow(!0), this.parent().find("#undo")[0].parent()._layout.applyClasses(this.parent().find("#undo")[0].parent()))
            }

            var x, y, b, w, _, E, C = [
                {name: "main", hidden: !1, items: [
                    {type: "buttongroup", items: [
                        {text: "Save", name: "savebtn", icon: "save", onclick: u("save")},
                        {text: "Revert", name: "revert", onclick: function () {
                            b.revert()
                        }, disabled: !0},
                        {text: "Undo", name: "undo", icon: "undo", onclick: function () {
                            b.undo()
                        }, disabled: !0},
                        {text: "Redo", name: "redo", icon: "redo", onclick: function () {
                            b.redo()
                        }, disabled: !0}
                    ]},
                    {type: "spacer", flex: 1},
                    {text: "Zoom fit", type: "button", onclick: function () {
                        s({value: "fit"})
                    }},
                    {type: "slider", name: "zoom", label: "Zoom", ondrag: s, value: 1, minValue: 0, maxValue: 7, previewFilter: function (e) {
                        return Math.round(100 * e) + "%"
                    }}
                ]},
                {name: "save", hidden: !0, onsubmit: c, items: [
                    {name: "saveas", label: "Save as", type: "textbox"},
                    {type: "button", text: "Save", subtype: "primary", onclick: c},
                    {type: "button", text: "Cancel", onclick: p}
                ]},
                {name: "redeye", hidden: !0, items: [
                    {label: "Radius", type: "slider", onDrag: function (e) {
                        b.redeye(e.value)
                    }},
                    {type: "button", text: "Apply", subtype: "primary", onclick: f},
                    {type: "button", text: "Reset", onclick: p}
                ]},
                {name: "crop", hidden: !0, items: [
                    {label: "X", name: "x", type: "textbox", size: 4, value: "0"},
                    {label: "Y", name: "y", type: "textbox", size: 4, value: "0"},
                    {label: "W", name: "w", type: "textbox", size: 4, value: "0"},
                    {label: "H", name: "h", type: "textbox", size: 4, value: "0"},
                    {type: "button", text: "Apply", subtype: "primary", onclick: f},
                    {type: "button", text: "Reset", onclick: p}
                ]},
                {name: "resize", hidden: !0, items: [
                    {label: "W", name: "rw", type: "textbox", size: 4, onchange: g},
                    {label: "H", name: "rh", type: "textbox", size: 4, onchange: g},
                    {name: "constrain", checked: !0, text: "Constrain proportions", type: "checkbox"},
                    {type: "button", text: "Apply", subtype: "primary", onclick: f},
                    {type: "button", text: "Reset", onclick: p}
                ]},
                {name: "fliprotate", hidden: !0, items: [
                    {type: "button", icon: "flip-h", text: "Flip H", onclick: function () {
                        b.flip("h")
                    }},
                    {type: "button", icon: "flip-v", text: "Flip V", onclick: function () {
                        b.flip("v")
                    }},
                    {type: "button", icon: "rotate-left", text: "Rotate left", onclick: function () {
                        b.rotate(-90)
                    }},
                    {type: "button", icon: "rotate-right", text: "Rotate right", onclick: function () {
                        b.rotate(90)
                    }},
                    {type: "button", text: "Apply", subtype: "primary", onclick: f},
                    {type: "button", text: "Reset", onclick: p}
                ]}
            ], R = [
                {name: "brightness", title: "Brightness", min: -1, max: 1, value: 0},
                {name: "contrast", title: "Contrast", min: -1, max: 1, value: 0},
                {name: "exposure", title: "Exposure", min: 0, max: 2, value: 2},
                {name: "gamma", title: "Gamma", min: 0, max: 2, value: 1},
                {name: "hue", title: "Hue", min: -1, max: 1, value: 0},
                {name: "saturate", title: "Saturate", min: -1, max: 1, value: 0},
                {name: "sepia", title: "Sepia", min: 0, max: 1, value: 0},
                {name: "vibrance", title: "Vibrance", min: -1, max: 1, value: 0},
                {name: "triangleblur", title: "TriangleBlur", min: 0, max: 200, value: 0},
                {name: "colorize", title: "Colorize", min: 0, max: 2, value: 1},
                {name: "grayscale", title: "Grayscale"},
                {name: "invert", title: "Invert"},
                {name: "sharpen", title: "Sharpen"},
                {name: "emboss", title: "Emboss"}
            ];
            i.each(R, function (e) {
                "colorize" == e.name ? C.push({name: "colorize", hidden: !0, defaults: {minValue: e.min, maxValue: e.max, value: e.value}, onPostRender: m, items: [
                    {type: "slider", label: "Red", name: "r"},
                    {type: "slider", label: "Green", name: "g"},
                    {type: "slider", label: "Blue", name: "b"},
                    {type: "button", text: "Apply", subtype: "primary", onclick: f},
                    {type: "button", text: "Reset", onclick: p}
                ]}) : e.value === t ? C.push({name: e.name, hidden: !0, items: [
                    {text: e.title, type: "label", strong: !0},
                    {type: "button", text: "Apply", subtype: "primary", onclick: f},
                    {type: "button", text: "Reset", onclick: p}
                ]}) : C.push({name: e.name, items: [
                    {type: "slider", label: e.title, minValue: e.min, maxValue: e.max, value: e.value, onPostRender: h(e.name)},
                    {type: "button", text: "Apply", subtype: "primary", onclick: f},
                    {type: "button", text: "Reset", onclick: p}
                ]})
            });
            var T = [];
            i.each(R, function (e) {
                T.push({name: e.name, text: e.title, onclick: u(e.name)})
            }), y = n.create("window", {layout: "border", minWidth: 800, minHeight: 600, title: "Edit image", items: [
                {region: "north", type: "panel", layout: "flex", padding: 4, border: "0 0 1 0", defaults: {type: "form", direction: "row", spacing: 4, labelGap: 3, padding: 3, align: "center", hidden: !0}, items: C},
                {region: "west", type: "panel", width: 200, border: "0 1 0 0", items: [
                    {type: "treeview", autoScroll: !0, items: [
                        {text: "Alter", fixed: !0, items: [
                            {text: "Resize", onclick: u("resize")},
                            {text: "Crop", onclick: u("crop")},
                            {text: "Flip/Rotate", onclick: u("fliprotate")}
                        ]},
                        {text: "Filters", fixed: !0, items: T}
                    ]}
                ]},
                {region: "center", type: "imagecanvas", onchange: d, onPostRender: v, layout: "fit"}
            ], onsubmit: function (e) {
                e.preventDefault()
            }}).renderTo(document.body).reflow(), y.find("imagecanvas")[0].loadFromPath(e.path, function (e) {
                this.zoom("fit"), this.hasCanvasSupport() && this._imageEditor.loadFromImage(e)
            }), b = y.find("imagecanvas")[0], b.on("cropchange", function (e) {
                y.find("#x").value(e.rect.x), y.find("#y").value(e.rect.y), y.find("#w").value(e.rect.w), y.find("#h").value(e.rect.h)
            }), window.viewport = y
        }})
    }),i(fn, [$t, Q, Gt], function (e, t, n) {
        function i(i) {
            return function (r) {
                function o() {
                    moxman.Env.baseUrl = t.baseUrl, moxman.util.JsonRpc.url = getMoxieManagerApiPage(), moxman.ui.Control.translate = n.translate, moxman.ui.FloatPanel.zIndex = r.zIndex || moxman.ui.FloatPanel.zIndex;
                    var e = new moxman.Manager(r);
                    e.init(new moxman.views[i](e))
                }

                if (r = r || {}, !t.baseUrl)for (var a = document.getElementsByTagName("script"), s = 0; a.length > s; s++) {
                    var l = a[s].src;
                    if (/(^|\/)moxman\./.test(l)) {
                        t.baseUrl = l.substring(0, l.lastIndexOf("/")) + "/..";
                        break
                    }
                }
                moxman.ui ? o() : e.load({js: [t.baseUrl + "/js/moxman.api.min.js", getMoxieManagerApiPage() + "?act=language" + (r.language ? "&code=" + r.language : ""), getMoxieManagerApiPage() + "?act=PluginJs"], css: [t.baseUrl + "/skins/lightgray/skin" + (t.ie7 ? ".ie7" : "") + ".min.css"]}, o)
            }
        }

        var r = this || window, o = {browse: i("FileListView"), upload: i("UploadView"), edit: i("EditView"), zip: i("ZipView"), createDir: i("CreateDirView"), createDoc: i("CreateDocView"), view: i("GalleryView"), rename: i("RenameView")};
        r.moxman = r.moxman || {};
        for (var a in o)r.moxman[a] = o[a];
        return r.moxman.addI18n = n.add, o
    }),i(pn, [ln, fn, _, qt, Z, on, tn, K, l, At, Wt, Zt, Tt, Qt, Yt], function (e, n, i, r, o, a, s, l, u, c, d, f, p, h, m) {
        function g(e, t) {
            var n = t.meta.url;
            return e.no_host = e.no_host || e.remove_script_host, e.relative_urls ? n = h.parseURI(e.document_base_url || e.default_base_url).toRelative(n) : (e.document_base_url || e.no_host) && (n = h.parseURI(e.document_base_url).toAbsolute(h.parseURI(n).getURI(!0), e.no_host)), t.url = t.meta.url = n, t
        }

        function v(e, t) {
            return t && !/^https?:\/\//.test(t) && (t = h.parseURI(e.document_base_url).toAbsolute(h.parseURI(t).getURI(!0))), t
        }

        return e.extend({init: function (e) {
            this.manager = e
        }, renderAsDialog: function (p) {
            function h(e) {
                pt || (pt = new d(st.getEl()).show(e || 500))
            }

            function x() {
                pt && (pt.hide(), pt = null)
            }

            function y() {
                return b()[0]
            }

            function b() {
                function e(e) {
                    var n;
                    return ut.each(function (i) {
                        return i.name == e ? (n = i, !1) : t
                    }), n
                }

                for (var n = new s, i = st.find("grid:visible,thumbnailview:visible")[0], r = i.selected(), o = 0; r.length > o; o++)n.add(e(r[o].name));
                return n
            }

            function w(e, t) {
                st.find("#manage").hide(), a.listFiles({url: p.url, path: e, filter: st.find("#filter").value(), include_directory_pattern: p.include_directory_pattern, exclude_directory_pattern: p.exclude_directory_pattern, include_file_pattern: p.include_file_pattern, exclude_file_pattern: p.exclude_file_pattern, extensions: p.extensions}, function (e) {
                    p.url = null, lt = e.file, ft.currentDir = lt, ut = e.files, dt = e.config, st.find("grid,thumbnailview").data(U(e.files));
                    var n = st.find("#filesystems")[0];
                    n.items().each(function (e) {
                        e.selected(e.settings.data.fileSystem === lt.fileSystem)
                    }), Y(), t && (t({files: e.files}), e.urlFile && M(e.urlFile))
                })
            }

            function _() {
                n.edit({path: b()[0].path, onbeforesave: h, onsave: function () {
                    W(x)
                }})
            }

            function E() {
                n.rename({from: b()[0].path, onbeforerename: h, onrename: function (e) {
                    w(lt.path, function () {
                        M(e.to), x()
                    })
                }})
            }

            function C() {
                n.upload({path: lt.path, onupload: function (e) {
                    w(lt.path, function () {
                        I(e.files), x()
                    })
                }})
            }

            function R() {
                b().download()
            }

            function T() {
                n.zip({from: b().toPathArray(), to: lt.path, onbeforecreate: h, oncreate: function (e) {
                    w(lt.path, function () {
                        M(e.to), x()
                    })
                }})
            }

            function A() {
                b()[0].unzip(lt.path, function (e) {
                    w(lt.path, function () {
                        I(e.to)
                    })
                })
            }

            function k() {
                var e = b();
                e.length > 0 && c.confirm("Are you sure you want to delete the selected files?", function (t) {
                    t && (h(), e.remove(function () {
                        W(function () {
                            x()
                        })
                    }))
                })
            }

            function S() {
                n.view({path: lt.path, file: y(), filter: st.find("#filter").value(), include_directory_pattern: p.include_directory_pattern, exclude_directory_pattern: p.exclude_directory_pattern, include_file_pattern: p.include_file_pattern, exclude_file_pattern: p.exclude_file_pattern, extensions: p.extensions})
            }

            function D() {
                var e = b();
                e.length > 0 && (ct = {path: lt.path, action: "cut", files: e}, st.find("#paste").show().parent().reflow())
            }

            function F() {
                var e = b();
                e.length > 0 && (ct = {path: lt.path, action: "copy", files: e}, st.find("#paste").show().parent().reflow())
            }

            function I(e) {
                var n = st.find("grid:visible,thumbnailview:visible")[0];
                e.each(function (e) {
                    var i, r = n.data();
                    for (i = r.length; i--;)if (r[i].name === e.name)return n.select(i), t
                })
            }

            function M(e) {
                var n = st.find("grid:visible,thumbnailview:visible")[0], i, r = n.data();
                for (i = r.length; i--;)if (r[i].name === e.name)return n.select(i), t
            }

            function N(e) {
                var n = st.find("grid:visible,thumbnailview:visible")[0], i, r = n.data();
                for (i = r.length; i--;)if (r[i].path === e)return n.select(i), t
            }

            function H() {
                ct && (lt.path != ct.path ? (h(), "cut" == ct.action ? ct.files.moveTo(lt.path, function (e) {
                    w(lt.path, function () {
                        I(e.to), x()
                    })
                }) : ct.files.copyTo(lt.path, function (e) {
                    w(lt.path, function () {
                        I(e.to), x()
                    })
                })) : I(ct.files), ct = null, st.find("#paste").hide().parent().reflow())
            }

            function P() {
                h(), b().populateMetaData({insert: !0}, function (e) {
                    m.populateImageSizes(e, function (e) {
                        var t = y();
                        x(), st.close(), e.each(function (e) {
                            g(p, e)
                        }), g(p, t), p.fields && u.each(p.fields.split(/[, ]/), function (e) {
                            var n;
                            if (n = document.getElementById(e))if (n.value = t.url, "fireEvent"in n)n.fireEvent("onchange"); else {
                                var i = document.createEvent("HTMLEvents");
                                i.initEvent("change", !1, !0), n.dispatchEvent(i)
                            }
                        }), p.oninsert && p.oninsert({focusedFile: t, files: e})
                    })
                })
            }

            function O(e) {
                n.createDir({path: lt.path, template: e, onbeforecreate: h, oncreate: function (e) {
                    w(lt.path, function () {
                        M(e.file), x()
                    })
                }})
            }

            function L(e) {
                n.createDoc({path: lt.path, template: e, onbeforecreate: h, oncreate: function (e) {
                    w(lt.path, function () {
                        M(e.file), x()
                    })
                }})
            }

            function W(e) {
                w(lt.path, e)
            }

            function z(e) {
                var t = e.isFile ? e.parentPath : e.path;
                w(t, function () {
                    M(e)
                })
            }

            function B() {
                h(), a.getFileSystems(function (e) {
                    function t(e) {
                        h(), w(e, function () {
                            "thumbs" == p.view && q(), x()
                        })
                    }

                    function n(e) {
                        return/^\/(History|Favorites|Uploaded)$/.test(e.rootPath) ? e.rootName : p.rootpath && 0 === p.rootpath.indexOf(e.rootPath) ? {raw: o.basename(p.rootpath)} : {raw: e.rootName}
                    }

                    function i(e) {
                        return p.rootpath && 0 === p.rootpath.indexOf(e.rootPath) ? p.rootpath : e.rootPath
                    }

                    function r() {
                        t(i(this.settings.data.fileSystem))
                    }

                    var a, s, l = st.find("#filesystems")[0];
                    for (a = 0; e.length > a; a++) {
                        var u, c;
                        p.path && 0 === p.path.indexOf(e[a].rootPath) && (t(p.path), s = !0), c = e[a].meta["ui.icon_16x16"] || "folder", u = e[a].meta["ui.icon_16x16_url"], l.append({text: n(e[a]), data: {fileSystem: e[a]}, icon: c, iconUrl: u, onclick: r}), e[a].meta.standalone === !1 && st.find("#logout").hide()
                    }
                    s || t(i(e[0]))
                }, l.errorHandler)
            }

            function U(e) {
                var t = [];
                return e.each(function (e) {
                    t.push({name: e.name, size: e.isFile ? e.size : -1, isFile: e.isFile, lastModified: e.lastModified, canPreview: e.canPreview, info: e.info, path: e.path, type: e.isFile ? o.extname(e.name).substr(1) : "dir"})
                }), t
            }

            function j(e, t) {
                var n = e.selected();
                t.unselect(0, t.data().length);
                for (var i = 0; n.length > i; i++)for (var r = t.data(), o = 0; r.length > o; o++)if (r[o].name == n[i].name) {
                    t.select(o);
                    break
                }
            }

            function V() {
                var e = st.find("#toggleviews")[0];
                st.find("thumbnailview").hide(), st.find("grid").show().parent().reflow(), j(st.find("thumbnailview")[0], st.find("grid")[0]), e.items()[0].active(!0), e.items()[1].active(!1), st.reflow()
            }

            function q() {
                var e = st.find("#toggleviews")[0], t = st.find("grid")[0], n = st.find("thumbnailview")[0];
                n._sortBy = t._sortBy, n._sortOrder = t._sortOrder, t.hide(), n.show().parent().reflow(), j(t, n), e.items()[0].active(!1), e.items()[1].active(!0), st.reflow()
            }

            function X(e) {
                w(e.value.path)
            }

            function G(e) {
                var t = [], n, i, r = e.data;
                if (lt) {
                    for (n = lt.path; n && (!p.rootpath || n !== o.dirname(p.rootpath));)t.push({name: o.basename(n), path: n}), n = o.dirname(n);
                    st.find("path").data(t.reverse());
                    var a = [], s = [];
                    for (i = 0; r.length > i; i++)r[i].isFile ? a.push(r[i]) : s.push(r[i]);
                    if (e.data = r = s.concat(a), lt.parentPath) {
                        if (p.rootpath && p.rootpath === lt.path)return;
                        for (i = r.length; i--;)if (r[i].isParent) {
                            r.splice(i, 1);
                            break
                        }
                        r.unshift({name: "..", size: -1, isFile: !1, isParent: !0, lastModified: new Date})
                    }
                }
            }

            function Y() {
                var e = st.find("#manage");
                b().length > 0 ? (p.multiple === !1 && b().length > 1 ? st.statusbar.find("#insert").disabled(!0) : st.statusbar.find("#insert").disabled(!1), e.show()) : (e.hide(), st.statusbar.find("#insert").disabled(!0)), st.find("#create,#upload").visible(lt.canWrite), tt(dt["general.hidden_tools"], dt["general.disabled_tools"]), e.parent().reflow()
            }

            function $(e) {
                var n;
                if (1 == e.cellIndex && e.inValue && (n = e.value, !e.row.isFile)) {
                    h();
                    var i = lt.path;
                    if (e.preventDefault(), e.row.info && e.row.info.link)return w(e.row.info.link, function () {
                        x()
                    }), t;
                    i = e.row.isParent ? o.dirname(i) : o.join(i, n), w(i, function () {
                        x()
                    })
                }
            }

            function J(e) {
                e.icon = e.row.isParent ? "parent" : e.row.isFile ? "file" : "folder", -1 != e.row.name.indexOf(".txt") && (e.icon = "file-text"), -1 != e.row.name.indexOf(".doc") && (e.icon = "file-word"), -1 != e.row.name.indexOf(".pdf") && (e.icon = "file-pdf"), -1 != e.row.name.indexOf(".xls") && (e.icon = "file-excel"), -1 != e.row.name.indexOf(".html") && (e.icon = "file-xml"), -1 != e.row.name.indexOf(".zip") && (e.icon = "file-zip"), /\.(jpe?g|png|gif)$/i.test(e.row.name) && (e.icon = "file-image"), e.row.canPreview && (e.row.thumbnail = !0)
            }

            function K(e) {
                e.row.isParent && (e.icon = "")
            }

            function Z(e) {
                return-1 == e ? "" : e > 1048576 ? Math.round(e / 1048576, 1) + " MB" : e > 1024 ? Math.round(e / 1024, 1) + " KB" : e + " b"
            }

            function Q(e) {
                var t, n = e.control.menu;
                n.append({text: "Folder", icon: "folder", onclick: function () {
                    O()
                }}), (t = dt["createdir.templates"]) && u.each(t.split(","), function (e) {
                    e = e.split("="), n.append({text: o.basename(e[0]), icon: "folder", onclick: function () {
                        O(e[1] || e[0])
                    }})
                }), (t = dt["createdoc.templates"]) && (n.append({text: "-"}), u.each(t.split(","), function (e) {
                    e = e.split("="), n.append({text: o.basename(e[0]), icon: "file", onclick: function () {
                        L(e[1] || e[0])
                    }})
                }))
            }

            function et(e) {
                "menu" == e.control.type && (e.control.parent().menu = null, e.control.remove())
            }

            function tt(e, t) {
                u.each("upload create manage filter".split(" "), function (e) {
                    st.find("#" + e).disabled(!1)
                }), e && u.each(e.split(","), function (e) {
                    st.find("#" + e).visible() && st.find("#" + e).hide()
                }), t && u.each(t.split(","), function (e) {
                    st.find("#" + e).disabled(!0)
                })
            }

            function nt(e) {
                var t = e.control.menu || this, n = ft.fire("BeforeRenderManageMenu", {menu: t});
                if (!n.isDefaultPrevented()) {
                    var i = [
                        {name: "clipboard", menu: [
                            {text: "Cut", icon: "cut", shortcut: "Ctrl+X", onclick: D},
                            {text: "Copy", icon: "copy", shortcut: "Ctrl+C", onclick: F},
                            {text: "Paste", name: "paste", icon: "paste", shortcut: "Ctrl+V", onclick: H, disabled: !0}
                        ]},
                        {name: "tools", menu: [
                            {text: "View", name: "view", icon: "view", onclick: S},
                            {text: "Edit", name: "edit", icon: "edit", onclick: _},
                            {text: "Rename", name: "rename", onclick: E},
                            {text: "Download", name: "download", icon: "download", onclick: R}
                        ]},
                        {name: "zip", menu: [
                            {text: "Zip", name: "zip", icon: "zip", onclick: T},
                            {text: "Unzip", name: "unzip", onclick: A}
                        ]},
                        {name: "remove", menu: [
                            {text: "Remove", name: "delete", icon: "delete", shortcut: "Delete", onclick: k}
                        ]}
                    ];
                    u.each(i, function (e, n) {
                        n > 0 && i.length - 1 >= n && t.append({text: "-"}), u.each(e.menu, function (e) {
                            t.append(e)
                        });
                        var r = ft.menuItems;
                        r && u.each(r, function (n) {
                            u.each(n.contexts, function (i) {
                                i == "manage." + e.name && t.append(n)
                            })
                        })
                    }), tt(dt["general.hidden_tools"], dt["general.disabled_tools"]), t.find("#paste").disabled(!ct), t.find("#unzip").disabled(".zip" != o.extname(y().name)), t.find("#edit").disabled(!y().canEdit), t.find("#rename").disabled(!y().canRename), t.find("#view").disabled(!y().canView)
                }
            }

            function it() {
                var e = [];
                e.push({text: "Local machine", icon: "upload", onclick: C});
                var t = ft.menuItems;
                return t && u.each(t, function (t) {
                    u.each(t.contexts, function (n) {
                        "upload" == n && (1 === e.length && e.push({text: "-"}), e.push(t))
                    })
                }), 1 === e.length ? {type: "button", name: "upload", text: "Upload", icon: "upload", onclick: C} : {type: "menubutton", name: "upload", text: "Upload", icon: "upload", menu: e}
            }

            function rt() {
                l.exec("logout", {}, function () {
                    st.close()
                })
            }

            function ot(e) {
                if (e.row.isFile) {
                    if (p.insert === !1)return;
                    e.control.unselect(), N(e.row.path), P()
                } else $(e)
            }

            var at, st, lt, ut, ct, dt = {}, ft = this.manager, pt;
            st = i.create("window", {title: p.title || "MoxieManager", minWidth: p.width || 800, minHeight: p.height || 500, defaults: {type: "panel", layout: "fit"}, layout: "border", items: [
                {region: "north", layout: "flex", border: "0 0 1 0", spacing: 4, padding: 4, items: [
                    {type: "menubutton", name: "create", text: "Create", icon: "create", onCreateMenu: Q, onhide: et},
                    it(),
                    {type: "button", icon: "paste", name: "paste", text: "Paste", hidden: !0, onclick: H},
                    {type: "menubutton", name: "manage", text: "Manage", icon: "manage", hidden: !0, onCreateMenu: nt, onhide: et},
                    {type: "spacer", flex: 1},
                    {type: "button", name: "refresh", icon: "refresh", tooltip: "Refresh file list", onclick: function () {
                        W()
                    }},
                    {type: "buttongroup", name: "toggleviews", items: [
                        {type: "button", icon: "list", active: !0, tooltip: "List", onclick: V},
                        {type: "button", icon: "thumbs", tooltip: "Thumbnails", onclick: q}
                    ]},
                    {type: "combobox", name: "filter", icon: "search", flex: 1, maxWidth: 200, placeholder: "Filter", onchange: function () {
                        W()
                    }},
                    {type: "button", name: "logout", icon: "logout", tooltip: "Logout", onclick: rt}
                ]},
                {region: "west", width: 190, border: "0 1 0 0", items: [
                    {type: "treeview", autoScroll: !0, items: [
                        {text: "FileSystems", name: "filesystems", fixed: !0, items: []}
                    ]}
                ]},
                {region: "center", layout: "flex", direction: "column", align: "stretch", items: [
                    {type: "panel", flex: 1, align: "stretch", border: "0 0 0 0", layout: "flex", direction: "column", autoResize: !1, items: [
                        {type: "path", name: "path", delimiter: "/", onSelect: X},
                        {type: "grid", border: "1 0 0 0", flex: 1, sortBy: "name", sortDir: "asc", columns: [
                            {name: "hidden", type: "checkbox", width: 25, sorting: !1, icon: "checkbox", filter: K},
                            {name: "name", title: "Filename", filter: J},
                            {name: "size", title: "Size", width: 60, filter: function (e) {
                                e.value = Z(e.value)
                            }},
                            {name: "type", title: "Type", width: 70},
                            {name: "lastModified", title: "Modification date", width: 200, filter: function (e) {
                                e.value = r.format(e.value, "%D %H:%M:%S")
                            }}
                        ], onBeforeUpdate: G, onSelect: Y, onCellClick: $},
                        {type: "thumbnailview", border: "1 0 0 0", flex: 1, hidden: !0, sortBy: "name", sortDir: "asc", nameField: "name", srcField: "src", minWidth: 10, minHeight: 10, filter: J, onBeforeUpdate: G, onSelect: Y, onThumbClick: ot}
                    ]}
                ]}
            ], onsubmit: function (e) {
                e.preventDefault()
            }, buttons: p.insert === !1 ? [] : [
                {text: "Insert", name: "insert", subtype: "primary", onclick: P, disabled: !0, autofocus: !0},
                {text: "Close", onclick: "close"}
            ]}), tt(p.hidden_tools, p.disabled_tools), st.renderTo(document.body).reflow().fullscreen(p.fullscreen === !0), p.filter && st.find("#filter").value(p.filter);
            var ht = new f(st);
            ht.add("ctrl+x", D), ht.add("ctrl+c", F), ht.add("ctrl+v", H), ht.add("delete", k), !p.url && p.fields && (p.multiple = !1, u.each(p.fields.split(/[, ]/), function (e) {
                var t;
                (t = document.getElementById(e)) && (p.url = v(p, t.value))
            })), B(), ft.getSelectedFiles = st.getSelectedFiles = b, ft.open = z, ft.refresh = W, ft.showThrobber = h, ft.hideThrobber = x, ft.selectByPath = N, e.hideThrobber = x, st.on("contextmenu", function (e) {
                function n(e, t, n) {
                    for (; e && e != t;) {
                        if (n(e))return e;
                        e = e.parentNode
                    }
                }

                function r(e) {
                    if (e)for (var t = e.parentNode.childNodes, n = t.length; n--;)if (t[n] === e)return n;
                    return-1
                }

                if (-1 != p.hidden_tools.indexOf("manage") && p.disabled_tools.indexOf(true))return e.preventDefault(), t;
                var o, a = e.control.type;
                if ("grid" != a && "thumbnailview" != a)return at && (at.remove(), at = null), t;
                if (e.preventDefault(), "grid" == a) {
                    if (o = r(n(e.target, e.control.getEl(), function (e) {
                        return"TR" == e.nodeName && -1 != e.className.indexOf("grid-row")
                    })), -1 === o || e.control.data()[o].isParent)return at && at.hide(), t;
                    e.control.isSelected(o) || (e.control.unselect(), e.control.select(o))
                } else {
                    if (o = r(n(e.target, e.control.getEl(), function (e) {
                        return-1 != e.className.indexOf("moxman-image")
                    })), -1 === o || e.control.data()[o].isParent)return at && at.hide(), t;
                    e.control.isSelected(o) || (e.control.unselect(), e.control.select(o))
                }
                if (at)at.show(); else {
                    var s = [];
                    at = new i.create({type: "menu", items: s, context: "contextmenu", onPostRender: nt, contrainToViewport: !0}), at.renderTo(document.body)
                }
                var l = {};
                l.x = e.clientX, l.y = e.clientY, at.moveTo(l.x, l.y), at.on("hide", function () {
                    at && (at.remove(), at = null)
                })
            })
        }})
    }),i(hn, [ln, _, on, Q], function (e, t, n, i) {
        return e.extend({renderAsDialog: function (e) {
            var r = [], o = [];
            n.getFile(e.path, function (n) {
                e.only_files = !0, e.extensions || (e.extensions = "jpg,gif,png,jpeg"), n.listFiles(e, function (n) {
                    for (var a = n.files, s = 0, l = 0; a.length > l; l++)e.file && e.file.path == a[l].path && (s = l), r.push({url: getMoxieManagerApiPage() + "?act=streamfile&path=" + encodeURIComponent(a[l].path) + "&u=" + a[l].size}), o.push({type: "image", text: a[l].name, url: getMoxieManagerApiPage() + "?act=streamfile&path=" + encodeURIComponent(a[l].path) + "&thumb=true&u=" + a[l].size});
                    t.create({type: "window", title: "View", defaults: {type: "panel", layout: "fit"}, layout: "border", minWidth: 800, minHeight: 600, items: [
                        {region: "center", type: "carousel", data: r, selectedIndex: s, onchange: function (e) {
                            var t = this;
                            window.setTimeout(function () {
                                var n = t.parent().items().eq(-1)[0].items();
                                n.eq(e.index)[0].active(!0).scrollIntoView("center"), n.eq(e.lastIndex).active(!1)
                            }, 0)
                        }},
                        {region: "south", height: 120, layout: "flex", pack: "center", align: "center", autoScroll: !0, padding: 10, spacing: 10, onclick: function (e) {
                            this.parent().items().eq(0)[0].index(this.items().indexOf(e.control))
                        }, items: o}
                    ]}).renderTo(document.body).reflow()
                })
            })
        }})
    }),i(mn, [ln, _, on, Z], function (e, t, n, i) {
        return e.extend({renderAsDialog: function (e) {
            n.getFile(e.from, function (n) {
                var r = "", o;
                n && n.isFile ? (r = i.extname(n.name), o = i.basename(n.name, r)) : o = n.name, t.create({type: "window", title: "Rename file", items: [
                    {type: "form", items: [
                        {label: "Filename", name: "newname", type: "textbox", minWidth: 300, value: o}
                    ]}
                ], onsubmit: function (t) {
                    e.onbeforerename && e.onbeforerename(), n.moveTo(n.parentPath, t.data.newname + r, function (t) {
                        e.onrename && e.onrename({from: n, to: t.to})
                    })
                }, buttons: [
                    {text: "Rename", subtype: "primary", onclick: "submit"},
                    {text: "Cancel", onclick: "close"}
                ]}).renderTo(document.body).reflow()
            })
        }})
    }),i(gn, [et, pt, at, tt, nt, ft, mt, lt], function (e, n, i, r, o, a, s, l) {
        function u(o) {
            var u = this, d, f, p;
            if ("string" == typeof o && (o = {browse_button: o}), f = i.get(o.browse_button), !f)throw new r.DOMException(r.DOMException.NOT_FOUND_ERR);
            p = {accept: [
                {title: a.translate("All Files"), extensions: "*"}
            ], name: "file", multiple: !1, required_caps: !1, container: f.parentNode || document.body}, o = "object" == typeof o ? e.extend({}, p, o) : p, "string" == typeof o.accept && (o.accept = n.mimes2extList(o.accept)), d = i.get(o.container), d || (d = document.body), "static" === i.getStyle(d, "position") && (d.style.position = "relative"), d = f = null, l.call(u), e.extend(u, {uid: e.guid("uid_"), ruid: null, files: null, init: function () {
                u.convertEventPropsToHandlers(c), u.bind("RuntimeInit", function (t, n) {
                    u.ruid = n.uid, u.bind("Change", function () {
                        var t = n.exec.call(u, "FileInput", "getFiles");
                        u.files = [], e.each(t, function (e) {
                            n.clients++, u.files.push(new s(u.ruid, e))
                        })
                    }, 999), n.exec.call(u, "FileInput", "init", o), u.bind("Refresh", function () {
                        var t, r, a;
                        a = i.get(o.browse_button), a && (t = i.getPos(a, i.get(o.container)), r = i.getSize(a), e.extend(n.getShimContainer().style, {top: t.y + "px", left: t.x + "px", width: r.w + "px", height: r.h + "px"}), a = null)
                    }), u.trigger("Refresh"), u.dispatchEvent("ready")
                }), u.connectRuntime(o)
            }, disable: function (e) {
                var n = this.getRuntime();
                n && n.exec.call(this, "FileInput", "disable", e === t ? !0 : e)
            }})
        }

        var c = ["ready", "change", "cancel", "mouseenter", "mouseleave", "mousedown", "mouseup"];
        return u.prototype = o.instance, u
    }),i(vn, [ft, at, tt, et, mt, lt, nt, pt], function (e, t, n, i, r, o, a, s) {
        function l(n) {
            var a = this, l;
            "string" == typeof n && (n = {drop_zone: n}), l = {accept: [
                {title: e.translate("All Files"), extensions: "*"}
            ], required_caps: {drag_and_drop: !0}}, n = "object" == typeof n ? i.extend({}, l, n) : l, n.container = t.get(n.drop_zone) || document.body, "static" === t.getStyle(n.container, "position") && (n.container.style.position = "relative"), "string" == typeof n.accept && (n.accept = s.mimes2extList(n.accept)), o.call(a), i.extend(a, {uid: i.guid("uid_"), ruid: null, files: null, init: function () {
                a.convertEventPropsToHandlers(u), a.bind("RuntimeInit", function (e, t) {
                    a.ruid = t.uid, a.bind("Drop", function () {
                        var e = t.exec.call(a, "FileDrop", "getFiles");
                        a.files = [], i.each(e, function (e) {
                            a.files.push(new r(a.ruid, e))
                        })
                    }, 999), t.exec.call(a, "FileDrop", "init", n), a.dispatchEvent("ready")
                }), a.connectRuntime(n)
            }})
        }

        var u = ["ready", "dragenter", "dragleave", "drop", "error"];
        return l.prototype = a.instance, l
    }),i(xn, [ln, Q, _, At, $, on, Z, gn, vn, ht, dt, ot, nt, st, Qt], function (e, n, i, r, o, a, s, l, u, c, d, f, p, h, m) {
        function g(e, t) {
            var n = t.meta.url;
            return e.no_host = e.no_host || e.remove_script_host, e.relative_urls ? n = m.parseURI(e.document_base_url || e.default_base_url).toRelative(n) : (e.document_base_url || e.no_host) && (n = m.parseURI(e.document_base_url).toAbsolute(m.parseURI(n).getURI(!0), e.no_host)), t.url = t.meta.url = n, t
        }

        function v(e) {
            var t;
            return"string" == typeof e && (e = /^([0-9]+)([mgk]?)$/.exec(e.toLowerCase().replace(/[^0-9mkg]/g, "")), t = e[2], e = +e[1], "g" == t && (e *= 1073741824), "m" == t && (e *= 1048576), "k" == t && (e *= 1024)), e
        }

        function x(e) {
            return-1 == e ? "" : e > 1048576 ? Math.round(e / 1048576, 1) + " MB" : e > 1024 ? Math.round(e / 1024, 1) + " KB" : e + " b"
        }

        return e.extend({renderAsDialog: function (e) {
            var m = h.getConstructor("html5"), y = (new m).can("drag_and_drop");
            f.swf_url = n.baseUrl + "/js/Moxie.swf", f.global_event_dispatcher = "moxman.dispatchEvent", window.moxman.dispatchEvent = p.instance.dispatchEvent, a.getFile(e.path, function (f) {
                f.getConfig(function (f) {
                    function p(e) {
                        for (var t = s.extname(e).substr(1), n = 0; b.length > n; n++)if ("*" == b[n] || b[n] == t)return!0;
                        return!1
                    }

                    function h(e) {
                        var t, n = k.find("#dragmsg"), i = k.find("#files")[0], r = 0, o;
                        for (n.hide(), i.show(), t = 0; e.length > t; t++)o = "", p(e[t].name) || (o = "Invalid extension"), i.append({type: "container", layout: "flex", spacing: 10, align: "stretch", style: "border: 1px solid #CCC", border: "0 0 1 0", padding: "7 15 7 15", items: [
                            {type: "label", flex: 1, maxWidth: 350, minHeight: 25, text: {raw: e[t].name}},
                            {type: "spacer", flex: 1},
                            o ? {type: "label", style: "color: #AA0000; text-decoration: underline", text: "Error", tooltip: o, maxWidth: 150} : {type: "progress", minWidth: 150}
                        ]}), o ? m.push({error: o, size: 0}) : m.push(e[t]);
                        for (i.reflow(), t = 0; m.length > t; t++)r += m[t].size;
                        k.find("#filecount")[0].text(["Files: {0}", m.length + " (" + x(r) + ")"]).parent().reflow()
                    }

                    var m = [], b = [], w, _, E, C, R;
                    w = f["upload.extensions"].toLowerCase().split(/\s*,\s*/), _ = f["filesystem.extensions"].toLowerCase().split(/\s*,\s*/), C = v(f["upload.chunk_size"]), "*" == w[0] && (w = _), "*" == _[0] && (_ = w), E = f["upload.maxsize"].replace(/\s+/, ""), E = E.replace(/([0-9]+)/g, "$1 ");
                    for (var T = 0; w.length > T; T++) {
                        w[T] = w[T].toLowerCase();
                        for (var A = 0; _.length > A; A++)if (_[A] = _[A], w[T] == _[A]) {
                            b.push(w[T]);
                            break
                        }
                    }
                    var k = i.create({type: "window", title: "Upload", minWidth: 600, maxHeight: 400, layout: "flex", direction: "column", align: "stretch", padding: 10, spacing: 10, items: [
                        {type: "label", text: ["Valid extensions: {0}", b.join(", ")], maxWidth: 600},
                        {type: "label", text: ["Max size: {0}", E]},
                        y ? {type: "label", name: "dragmsg", text: "Drag files here", style: "text-align: center; border-style: dashed", minHeight: 200, maxHeight: 200, border: 1} : {type: "label", name: "dragmsg", text: "Select files to upload", style: "text-align: center", minHeight: 200, maxHeight: 200, border: 1},
                        {type: "panel", name: "files", layout: "flex", direction: "column", align: "stretch", autoScroll: !0, style: "border: 1px solid #CCC", minHeight: 200, maxHeight: 200, border: 1, hidden: !0},
                        {type: "container", layout: "flex", align: "stretch", items: [
                            {type: "label", name: "filecount", text: " ", flex: 1},
                            {type: "button", name: "addfiles", text: "Add files", icon: "upload"}
                        ]}
                    ], onsubmit: function (i) {
                        function s() {
                            return(new Date).getTime()
                        }

                        function l(i, a, f, m) {
                            var g = i.slice(m, m + C), _ = new d;
                            _.append("file", g), R = new c, R.open("POST", getMoxieManagerApiPage() + "?act=upload&path=" + encodeURIComponent(e.path) + "&name=" + encodeURIComponent(i.name) + "&loaded=" + m + "&total=" + i.size + "&id=" + a), R.upload.onprogress = function (e) {
                                var t = e.loaded - (e.total - g.size);
                                w = Math.ceil(m + t / ((s() - b) / 1e3)), f.items().filter("progress").value(Math.round(100 * ((m + t) / i.size))), k.find("#filecount").text("Uploading file: " + (p + 1) + "/" + y + " at " + x(w) + "/s")
                            }, R.onload = function () {
                                var e;
                                R.upload.onprogress({loaded: g.size, total: g.size});
                                try {
                                    e = o.parse(R.responseText)
                                } catch (n) {
                                    return r.alert("Error response is not proper JSON\n\nResponse:\n" + R.responseText), t
                                }
                                e.error && (f.append({type: "label", style: "color: #AA0000; text-decoration: underline", text: "Error", tooltip: e.error.message, maxWidth: 150}), f.items().filter("progress")[0].remove(), f.reflow(), h++), f.scrollIntoView(), !e.error && i.size > m + C ? l(i, e.result, f, m + C) : (e.result && v.push(e.result), u())
                            }, R.send(_)
                        }

                        function u() {
                            var n = m.shift();
                            return p++, n && n.error ? (u(), t) : (n ? l(n, null, f.items()[p], 0) : (k.find("#filecount").text(["Uploaded {0} files(s) at {1}/s", y, x(w)]), h && r.alert("Some of the files failed to upload. Check the upload status of each file."), e.onupload && a.convertResult(v, function (t) {
                                t.each(function (t) {
                                    g(e, t)
                                }), e.onupload({files: t})
                            })), t)
                        }

                        var f = k.find("#files")[0], p = -1, h = 0, v = [], y = m.length, b, w;
                        b = s(), i.preventDefault(), S.disable(!0), k.find("#addfiles").disabled(!0), k.statusbar.find("#upload").hide(), u()
                    }, buttons: [
                        {text: "Upload", name: "upload", subtype: "primary", onclick: "submit"},
                        {text: "Close", onclick: function () {
                            R && R.abort(), k.close()
                        }}
                    ]}).renderTo(document.body).reflow(), S = new l({browse_button: k.find("#addfiles")[0]._id, accept: [
                        {title: "Image files", extensions: b.join(",")}
                    ], multiple: !0});
                    if (S.onchange = function (e) {
                        h(e.target.files)
                    }, S.init(), y) {
                        var D = new u(k._id);
                        D.ondrop = function () {
                            h(this.files)
                        }, D.init()
                    }
                })
            })
        }})
    }),i(yn, [ln, _, on, tn, Z], function (e, t, n, i, r) {
        return e.extend({renderAsDialog: function (e) {
            t.create({type: "window", title: "Create zip file", items: [
                {type: "form", items: [
                    {label: "Filename", name: "name", type: "textbox", minWidth: 300}
                ]}
            ], onsubmit: function (t) {
                e.onbeforecreate && e.onbeforecreate(), n.getFiles(e.from, function (n) {
                    n.zip(r.join(e.to, t.data.name), function (t) {
                        e.oncreate({from: n, to: t.to})
                    })
                })
            }, buttons: [
                {text: "Create", subtype: "primary", onclick: "submit"},
                {text: "Cancel", onclick: "close"}
            ]}).renderTo(document.body).reflow()
        }})
    }),i(bn, [], function () {
        function e(e) {
            for (var n = t, i = e.split(/[.\/]/), r = 0; i.length > r; ++r) {
                if (!n[i[r]])return;
                n = n[i[r]]
            }
            return n
        }

        var t = this || window, n = {plugins: {}, require: function (t, n) {
            for (var i = 0; t.length > i; i++)if (t[i] = e(t[i]), !t[i])throw"module definition dependecy not found: " + t[i];
            n.apply(null, t)
        }, add: function (e, t) {
            this.plugins[e] = t
        }};
        return t.moxman = t.moxman || {}, t.moxman.require = n.require, n
    }),i(wn, [Kt, bn, l], function (e, t, n) {
        return function (i) {
            var r = [];
            n.extend(this, e), this.init = function (e) {
                var n = t.plugins;
                for (var r in n)n[r](this);
                this.view = e.render(i)
            }, this.addMenuItem = function (e) {
                r.push(e)
            }, this.menuItems = r
        }
    }),i(_n, [et, tt, st, ot], function (e, n, i, r) {
        function o(o) {
            var l = this, u;
            i.call(this, a, o, {access_binary: !!(window.FileReader || window.File && window.File.getAsDataURL), access_image_binary: function () {
                return l.can("access_binary") && !!s.Image
            }, display_media: r.can("create_canvas") || r.can("use_data_uri_over32kb"), drag_and_drop: function () {
                var e = document.createElement("div");
                return("draggable"in e || "ondragstart"in e && "ondrop"in e) && ("IE" !== r.browser || r.version > 9)
            }(), return_response_type: function (e) {
                return"json" === e ? !0 : r.can("return_response_type", e)
            }, report_upload_progress: function () {
                return!(!window.XMLHttpRequest || !(new XMLHttpRequest).upload)
            }, resize_image: function () {
                return l.can("access_binary") && r.can("create_canvas")
            }, select_folder: "Chrome" === r.browser && r.version >= 21, select_multiple: !("Safari" === r.browser && "Windows" === r.OS), send_binary_string: !(!window.XMLHttpRequest || !((new XMLHttpRequest).sendAsBinary || window.Uint8Array && window.ArrayBuffer)), send_custom_headers: !!window.XMLHttpRequest, send_multipart: function () {
                return!!(window.XMLHttpRequest && (new XMLHttpRequest).upload && window.FormData) || can("send_binary_string")
            }, slice_blob: !(!window.File || !(File.prototype.mozSlice || File.prototype.webkitSlice || File.prototype.slice)), stream_upload: function () {
                return l.can("slice_blob") && l.can("send_multipart")
            }, summon_file_dialog: function () {
                return"Firefox" === r.browser && r.version >= 4 || "Opera" === r.browser && r.version >= 12 || !!~e.inArray(r.browser, ["Chrome", "Safari"])
            }(), upload_filesize: !0}), e.extend(this, {init: function () {
                return window.File && r.can("use_fileinput") ? (this.trigger("Init"), t) : (this.trigger("Error", new n.RuntimeError(n.RuntimeError.NOT_INIT_ERR)), t)
            }, getShim: function () {
                return u
            }, shimExec: function (e, t) {
                var n = [].slice.call(arguments, 2);
                return l.getShim().exec.call(this, this.uid, e, t, n)
            }, destroy: function (e) {
                return function () {
                    u && u.removeAllInstances(l), e.call(l), e = u = l = null
                }
            }(this.destroy)}), u = e.extend(function () {
                var n = {};
                return{exec: function (e, i, r, o) {
                    return u[i] && (n[e] || (n[e] = {context: this, instance: new u[i]}), n[e].instance[r]) ? n[e].instance[r].apply(this, o) : t
                }, removeInstance: function (e) {
                    delete n[e]
                }, removeAllInstances: function () {
                    var t = this;
                    e.each(n, function (n, i) {
                        "function" === e.typeOf(n.instance.destroy) && n.instance.destroy.call(n.context), t.removeInstance(i)
                    })
                }}
            }(), s)
        }

        var a = "html5", s = {};
        return i.addConstructor(a, o), s
    }),i(En, [_n, ct], function (e, t) {
        function n() {
            function e(e, t, n) {
                var i;
                if (!window.File.prototype.slice)return(i = window.File.prototype.webkitSlice || window.File.prototype.mozSlice) ? i.call(e, t, n) : null;
                try {
                    return e.slice(), e.slice(t, n)
                } catch (r) {
                    return e.slice(t, n - t)
                }
            }

            this.slice = function () {
                return new t(this.getRuntime().uid, e.apply(this, arguments))
            }
        }

        return e.Blob = n
    }),i(Cn, [et], function (e) {
        function n() {
            this.returnValue = !1
        }

        function i() {
            this.cancelBubble = !0
        }

        var r = {}, o = "moxie_" + e.guid(), a = function (t, a, s) {
            var l, u, c;
            c = arguments[3], a = a.toLowerCase(), t.addEventListener ? (l = s, t.addEventListener(a, l, !1)) : t.attachEvent && (l = function () {
                var e = window.event;
                e.target || (e.target = e.srcElement), e.preventDefault = n, e.stopPropagation = i, s(e)
            }, t.attachEvent("on" + a, l)), t[o] || (t[o] = e.guid()), r.hasOwnProperty(t[o]) || (r[t[o]] = {}), u = r[t[o]], u.hasOwnProperty(a) || (u[a] = []), u[a].push({func: l, orig: s, key: c})
        }, s = function (n, i) {
            var a, s, l;
            if ("function" == typeof arguments[2] ? s = arguments[2] : l = arguments[2], i = i.toLowerCase(), n[o] && r[n[o]] && r[n[o]][i]) {
                a = r[n[o]][i];
                for (var u = a.length - 1; u >= 0 && (a[u].key !== l && a[u].orig !== s || (n.removeEventListener ? n.removeEventListener(i, a[u].func, !1) : n.detachEvent && n.detachEvent("on" + i, a[u].func), a[u].orig = null, a[u].func = null, a.splice(u, 1), s === t)); u--);
                if (a.length || delete r[n[o]][i], e.isEmptyObj(r[n[o]])) {
                    delete r[n[o]];
                    try {
                        delete n[o]
                    } catch (c) {
                        n[o] = t
                    }
                }
            }
        }, l = function (t) {
            var n = arguments[1];
            t && t[o] && e.each(r[t[o]], function (e, i) {
                s(t, i, n)
            })
        };
        return{addEvent: a, removeEvent: s, removeAllEvents: l}
    }),i(Rn, [_n, et, at, Cn, pt], function (e, t, n, i, r) {
        function o() {
            function e(e) {
                var n = l.accept.mimes || r.extList2mimes(l.accept), i = e.type || r.getFileMime(e.name) || "";
                return n.length && -1 === t.inArray(i, n) ? !1 : !0
            }

            function n(e, n) {
                var i = [];
                t.each(e, function (e) {
                    i.push(function (t) {
                        o(e, t)
                    })
                }), t.inSeries(i, function (e) {
                    n()
                })
            }

            function o(t, n) {
                t.isFile ? t.file(function (t) {
                    e(t) && s.push(t), n()
                }, function (e) {
                    n()
                }) : t.isDirectory ? a(t, n) : n()
            }

            function a(e, t) {
                function i(e) {
                    o.readEntries(function (t) {
                        t.length ? ([].push.apply(r, t), i(e)) : e()
                    }, e)
                }

                var r = [], o = e.createReader();
                i(function () {
                    n(r, t)
                })
            }

            var s = [], l;
            t.extend(this, {init: function (r) {
                var o = this, a;
                l = r, a = l.container, i.addEvent(a, "dragover", function (e) {
                    e.preventDefault(), e.stopPropagation(), e.dataTransfer.dropEffect = "copy"
                }, o.uid), i.addEvent(a, "drop", function (i) {
                    if (i.preventDefault(), i.stopPropagation(), s = [], i.dataTransfer.items && i.dataTransfer.items[0].webkitGetAsEntry) {
                        var r = [];
                        t.each(i.dataTransfer.items, function (e) {
                            r.push(e.webkitGetAsEntry())
                        }), n(r, function () {
                            o.trigger("drop")
                        })
                    } else t.each(i.dataTransfer.files, function (t) {
                        e(t) && s.push(t)
                    }), o.trigger("drop")
                }, o.uid), i.addEvent(a, "dragenter", function (e) {
                    e.preventDefault(), e.stopPropagation(), o.trigger("dragenter")
                }, o.uid), i.addEvent(a, "dragleave", function (e) {
                    e.preventDefault(), e.stopPropagation(), o.trigger("dragleave")
                }, o.uid)
            }, getFiles: function () {
                return s
            }})
        }

        return e.FileDrop = o
    }),i(Tn, [_n, et, at, Cn, pt], function (e, t, n, i, r) {
        function o() {
            var e = [], o;
            t.extend(this, {init: function (a) {
                var s = this, l = s.getRuntime(), u, c, d;
                o = a, e = [], d = o.accept.mimes || r.extList2mimes(o.accept), c = l.getShimContainer(), c.innerHTML = '<input id="' + l.uid + '" type="file" style="font-size:999px;opacity:0;"' + (o.multiple && l.can("select_multiple") ? "multiple" : "") + (o.directory && l.can("select_folder") ? "webkitdirectory directory" : "") + ' accept="' + d.join(",") + '" />', u = n.get(l.uid), t.extend(u.style, {position: "absolute", top: 0, left: 0, width: "100%", height: "100%"}), function () {
                    var e, t, r;
                    e = n.get(o.browse_button), l.can("summon_file_dialog") && ("static" === n.getStyle(e, "position") && (e.style.position = "relative"), t = parseInt(n.getStyle(e, "z-index"), 10) || 1, e.style.zIndex = t, c.style.zIndex = t - 1, i.addEvent(e, "click", function (e) {
                        u && !u.disabled && u.click(), e.preventDefault()
                    }, s.uid)), r = l.can("summon_file_dialog") ? e : c, i.addEvent(r, "mouseover", function () {
                        s.trigger("mouseenter")
                    }, s.uid), i.addEvent(r, "mouseout", function () {
                        s.trigger("mouseleave")
                    }, s.uid), i.addEvent(r, "mousedown", function () {
                        s.trigger("mousedown")
                    }, s.uid), i.addEvent(n.get(o.container), "mouseup", function () {
                        s.trigger("mouseup")
                    }, s.uid)
                }(), u.onchange = function () {
                    e = [], o.directory ? t.each(this.files, function (t) {
                        "." !== t.name && e.push(t)
                    }) : e = [].slice.call(this.files), this.value = "", s.trigger("change")
                }
            }, getFiles: function () {
                return e
            }, disable: function (e) {
                var t = this.getRuntime(), i;
                (i = n.get(t.uid)) && (i.disabled = !!e)
            }, destroy: function () {
                var t = this.getRuntime(), r = t.getShimContainer();
                i.removeAllEvents(r, this.uid), i.removeAllEvents(n.get(o.container), this.uid), i.removeAllEvents(n.get(o.browse_button), this.uid), r.innerHTML = "", e = o = null
            }})
        }

        return e.FileInput = o
    }),i(An, [], function () {
        return!!window.JSON && JSON.parse || function () {
            var e, n, i = {'"': '"', "\\": "\\", "/": "/", b: "\b", f: "\f", n: "\n", r: "\r", t: "	"}, r, o = function (t) {
                throw{name: "SyntaxError", message: t, at: e, text: r}
            }, a = function (t) {
                return t && t !== n && o("Expected '" + t + "' instead of '" + n + "'"), n = r.charAt(e), e += 1, n
            }, s = function () {
                var e, i = "";
                for ("-" === n && (i = "-", a("-")); n >= "0" && "9" >= n;)i += n, a();
                if ("." === n)for (i += "."; a() && n >= "0" && "9" >= n;)i += n;
                if ("e" === n || "E" === n)for (i += n, a(), ("-" === n || "+" === n) && (i += n, a()); n >= "0" && "9" >= n;)i += n, a();
                return e = +i, isFinite(e) ? e : (o("Bad number"), t)
            }, l = function () {
                var e, t, r = "", s;
                if ('"' === n)for (; a();) {
                    if ('"' === n)return a(), r;
                    if ("\\" === n)if (a(), "u" === n) {
                        for (s = 0, t = 0; 4 > t && (e = parseInt(a(), 16), isFinite(e)); t += 1)s = 16 * s + e;
                        r += String.fromCharCode(s)
                    } else {
                        if ("string" != typeof i[n])break;
                        r += i[n]
                    } else r += n
                }
                o("Bad string")
            }, u = function () {
                for (; n && " " >= n;)a()
            }, c = function () {
                switch (n) {
                    case"t":
                        return a("t"), a("r"), a("u"), a("e"), !0;
                    case"f":
                        return a("f"), a("a"), a("l"), a("s"), a("e"), !1;
                    case"n":
                        return a("n"), a("u"), a("l"), a("l"), null
                }
                o("Unexpected '" + n + "'")
            }, d, f = function () {
                var e = [];
                if ("[" === n) {
                    if (a("["), u(), "]" === n)return a("]"), e;
                    for (; n;) {
                        if (e.push(d()), u(), "]" === n)return a("]"), e;
                        a(","), u()
                    }
                }
                o("Bad array")
            }, p = function () {
                var e, t = {};
                if ("{" === n) {
                    if (a("{"), u(), "}" === n)return a("}"), t;
                    for (; n;) {
                        if (e = l(), u(), a(":"), Object.hasOwnProperty.call(t, e) && o('Duplicate key "' + e + '"'), t[e] = d(), u(), "}" === n)return a("}"), t;
                        a(","), u()
                    }
                }
                o("Bad object")
            };
            return d = function () {
                switch (u(), n) {
                    case"{":
                        return p();
                    case"[":
                        return f();
                    case'"':
                        return l();
                    case"-":
                        return s();
                    default:
                        return n >= "0" && "9" >= n ? s() : c()
                }
            }, function (i, a) {
                var s;
                return r = i, e = 0, n = " ", s = d(), u(), n && o("Syntax error"), "function" == typeof a ? function l(e, n) {
                    var i, r, o = e[n];
                    if (o && "object" == typeof o)for (i in o)Object.prototype.hasOwnProperty.call(o, i) && (r = l(o, i), r !== t ? o[i] = r : delete o[i]);
                    return a.call(e, n, o)
                }({"": s}, "") : s
            }
        }()
    }),i(kn, [_n, et, mt, ct, dt, tt, ot, An], function (e, n, i, r, o, a, s, l) {
        function u() {
            function e(e) {
                var t = "----moxieboundary" + (new Date).getTime(), n = "--", i = "\r\n", o = "", s = this.getRuntime();
                if (!s.can("send_binary_string"))throw new a.RuntimeError(a.RuntimeError.NOT_SUPPORTED_ERR);
                return c.setRequestHeader("Content-Type", "multipart/form-data; boundary=" + t), e.each(function (e, a) {
                    o += e instanceof r ? n + t + i + 'Content-Disposition: form-data; name="' + a + '"; filename="' + unescape(encodeURIComponent(e.name || "blob")) + '"' + i + "Content-Type: " + e.type + i + i + e.getSource() + i : n + t + i + 'Content-Disposition: form-data; name="' + a + '"' + i + i + unescape(encodeURIComponent(e)) + i
                }), o += n + t + n + i
            }

            var u = this, c, d;
            n.extend(this, {send: function (i, a) {
                var l = this, f = !1, p, h, m, g = "Mozilla" === s.browser && s.version >= 4 && 7 > s.version, v = "Android Browser" === s.browser;
                return(g || v) && a instanceof o && a.hasBlob() && !a.getBlob().isDetached() && (h = a.getBlob().getSource(), h instanceof window.Blob && window.FileReader) ? (m = new window.FileReader, m.onload = function () {
                    a.append(a.getBlobName(), new r(null, {type: h.type, data: m.result})), u.send.call(l, i, a)
                }, m.readAsBinaryString(h), t) : (c = new window.XMLHttpRequest, d = i.url.replace(/^.+?\/([\w\-\.]+)$/, "$1").toLowerCase(), c.open(i.method, i.url, i.async, i.user, i.password), n.isEmptyObj(i.headers) || n.each(i.headers, function (e, t) {
                    c.setRequestHeader(t, e)
                }), "" !== i.responseType && (c.responseType = "json" !== i.responseType || s.can("return_response_type", "json") ? i.responseType : "text"), function () {
                    function e(e) {
                        l.trigger(e)
                    }

                    function t(e) {
                        l.trigger({type: "UploadProgress", loaded: e.loaded, total: e.total})
                    }

                    function i() {
                        n.each(r, function (t) {
                            c.removeEventListener(t, e)
                        }), c.removeEventListener("loadend", i), c.upload && c.upload.removeEventListener("progress", t), c = null
                    }

                    var r = ["loadstart", "progress", "abort", "error", "load", "timeout"];
                    n.each(r, function (t) {
                        c.addEventListener(t, e)
                    }), c.upload && c.upload.addEventListener("progress", t), c.addEventListener("loadend", i)
                }(), a instanceof r ? (a.isDetached() && (f = !0), a = a.getSource()) : a instanceof o && (a.hasBlob() && a.getBlob().isDetached() ? (a = e.call(l, a), f = !0) : (p = new window.FormData, a.each(function (e, t) {
                    e instanceof r ? p.append(t, e.getSource()) : p.append(t, e)
                }), a = p)), f ? c.sendAsBinary ? c.sendAsBinary(a) : function () {
                    for (var e = new Uint8Array(a.length), t = 0; a.length > t; t++)e[t] = 255 & a.charCodeAt(t);
                    c.send(e.buffer)
                }() : c.send(a), t)
            }, getStatus: function () {
                try {
                    if (c)return c.status
                } catch (e) {
                }
            }, getResponse: function (e) {
                var t = this.getRuntime();
                try {
                    if (c) {
                        if ("blob" === e) {
                            var n = new i(t.uid, c.response);
                            return n.name = d, n
                        }
                        return"json" !== e || s.can("return_response_type", "json") ? c.response : 200 === c.status ? l(c.response) : null
                    }
                } catch (r) {
                }
            }, abort: function () {
                c && c.abort()
            }, destroy: function () {
                u = d = null
            }})
        }

        return e.XMLHttpRequest = u
    }),i(Sn, [et, ot, tt, st], function (e, n, i, r) {
        function o(o) {
            function l() {
                var e;
                try {
                    e = navigator.plugins["Shockwave Flash"], e = e.description
                } catch (t) {
                    try {
                        e = new ActiveXObject("ShockwaveFlash.ShockwaveFlash").GetVariable("$version")
                    } catch (n) {
                        e = "0.0"
                    }
                }
                return e = e.match(/\d+/g), parseFloat(e[0] + "." + e[1])
            }

            var u = this, c;
            o = e.extend({swf_url: n.swf_url}, o), r.call(this, a, o, function () {
                function t() {
                    var e = o.required_features || {};
                    return e.access_binary || e.send_custom_headers || e.send_browser_cookies
                }

                return{access_binary: !0, access_image_binary: !0, display_media: !0, drag_and_drop: !1, report_upload_progress: !0, resize_image: !0, return_response_headers: !1, return_response_type: !0, return_status_code: !0, select_multiple: !0, send_binary_string: !0, send_browser_cookies: function () {
                    return t()
                }, send_custom_headers: function () {
                    return t()
                }, send_multipart: !0, slice_blob: !0, stream_upload: function (e) {
                    return!!e && !t()
                }, summon_file_dialog: !1, upload_filesize: function (n) {
                    var i = t() ? 2097152 : -1;
                    return!~i || i >= e.parseSizeStr(n) ? !0 : !1
                }, use_http_method: function (t) {
                    return!e.arrayDiff(t, ["GET", "POST"])
                }}
            }()), e.extend(this, {init: function () {
                var r, a, s;
                return 10 > l() ? (this.trigger("Error", new i.RuntimeError(i.RuntimeError.NOT_INIT_ERR)), t) : (s = this.getShimContainer(), e.extend(s.style, {position: "absolute", top: "-8px", left: "-8px", width: "9px", height: "9px", overflow: "hidden"}), r = '<object id="' + this.uid + '" type="application/x-shockwave-flash" data="' + o.swf_url + '" ', "IE" === n.browser && (r += 'classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" '), r += 'width="100%" height="100%" style="outline:0"><param name="movie" value="' + o.swf_url + '" />' + '<param name="flashvars" value="uid=' + escape(this.uid) + "&target=" + n.global_event_dispatcher + '" />' + '<param name="wmode" value="transparent" />' + '<param name="allowscriptaccess" value="always" />' + "</object>", "IE" === n.browser ? (a = document.createElement("div"), s.appendChild(a), a.outerHTML = r, a = s = null) : s.innerHTML = r, c = setTimeout(function () {
                    u && !u.initialized && u.trigger("Error", new i.RuntimeError(i.RuntimeError.NOT_INIT_ERR))
                }, 5e3), t)
            }, destroy: function (e) {
                return function () {
                    e.call(u), clearTimeout(c), o = c = e = u = null
                }
            }(this.destroy)}, s)
        }

        var a = "flash", s = {};
        return r.addConstructor(a, o), s
    }),i(Dn, [Sn, ct], function (e, t) {
        var n = {slice: function (e, n, i, r) {
            var o = this.getRuntime();
            return 0 > n ? n = Math.max(e.size + n, 0) : n > 0 && (n = Math.min(n, e.size)), 0 > i ? i = Math.max(e.size + i, 0) : i > 0 && (i = Math.min(i, e.size)), e = o.shimExec.call(this, "Blob", "slice", n, i, r || ""), e && (e = new t(o.uid, e)), e
        }};
        return e.Blob = n
    }),i(Fn, [Sn], function (e) {
        var t = {init: function (e) {
            return this.getRuntime().shimExec.call(this, "FileInput", "init", {name: e.name, accept: e.accept, multiple: e.multiple})
        }};
        return e.FileInput = t
    }),i(In, [et, it, lt, nt], function (e, t, n, i) {
        function r() {
            function i() {
                c = d = 0, u = this.result = null
            }

            function o(t, n) {
                var i = this;
                l = n, i.bind("TransportingProgress", function (t) {
                    d = t.loaded, c > d && -1 === e.inArray(i.state, [r.IDLE, r.DONE]) && a.call(i)
                }, 999), i.bind("TransportingComplete", function () {
                    d = c, i.state = r.DONE, u = null, i.result = l.exec.call(i, "Transporter", "getAsBlob", t || "")
                }, 999), i.state = r.BUSY, i.trigger("TransportingStarted"), a.call(i)
            }

            function a() {
                var e = this, n, i = c - d;
                f > i && (f = i), n = t.btoa(u.substr(d, f)), l.exec.call(e, "Transporter", "receive", n, c)
            }

            var s, l, u, c, d, f;
            n.call(this), e.extend(this, {uid: e.guid("uid_"), state: r.IDLE, result: null, transport: function (t, n, r) {
                var a = this;
                if (r = e.extend({chunk_size: 204798}, r), (s = r.chunk_size % 3) && (r.chunk_size += 3 - s), f = r.chunk_size, i.call(this), u = t, c = t.length, "string" === e.typeOf(r) || r.ruid)o.call(a, n, this.connectRuntime(r)); else {
                    var l = function (e, t) {
                        a.unbind("RuntimeInit", l), o.call(a, n, t)
                    };
                    this.bind("RuntimeInit", l), this.connectRuntime(r)
                }
            }, abort: function () {
                var e = this;
                e.state = r.IDLE, l && (l.exec.call(e, "Transporter", "clear"), e.trigger("TransportingAborted")), i.call(e)
            }, destroy: function () {
                this.unbindAll(), l = null, this.disconnectRuntime(), i.call(this)
            }})
        }

        return r.IDLE = 0, r.BUSY = 1, r.DONE = 2, r.prototype = i.instance, r
    }),i(Mn, [Sn, et, ct, mt, gt, dt, In, An], function (e, t, n, i, r, o, a, s) {
        var l = {send: function (e, i) {
            function r() {
                e.transport = c.can("send_browser_cookies") ? "browser" : "client", c.shimExec.call(u, "XMLHttpRequest", "send", e, i)
            }

            function s(e, t) {
                c.shimExec.call(u, "XMLHttpRequest", "appendBlob", e, t.uid), i = null, r()
            }

            function l(e, t) {
                var n = new a;
                n.bind("TransportingComplete", function () {
                    s(e, this.result)
                }), n.transport(t.getSource(), t.type, {ruid: c.uid})
            }

            var u = this, c = u.getRuntime();
            if (t.isEmptyObj(e.headers) || t.each(e.headers, function (e, t) {
                c.shimExec.call(u, "XMLHttpRequest", "setRequestHeader", t, "" + e)
            }), i instanceof o) {
                var d;
                if (i.each(function (e, t) {
                    e instanceof n ? d = t : c.shimExec.call(u, "XMLHttpRequest", "append", t, e)
                }), i.hasBlob()) {
                    var f = i.getBlob();
                    f.isDetached() ? l(d, f) : s(d, f)
                } else i = null, r()
            } else i instanceof n ? (i = i.uid, r()) : r()
        }, getResponse: function (e) {
            var n, o, a = this.getRuntime();
            if (o = a.shimExec.call(this, "XMLHttpRequest", "getResponseAsBlob")) {
                if (o = new i(a.uid, o), "blob" === e)return o;
                if (~t.inArray(e, ["", "text"]))return n = new r, n.readAsText(o);
                if ("arraybuffer" === e); else if ("json" === e) {
                    n = new r;
                    try {
                        return s(n.readAsText(o))
                    } catch (l) {
                        return null
                    }
                }
            }
            return null
        }, abort: function (e) {
            var t = this.getRuntime();
            t.shimExec.call(this, "XMLHttpRequest", "abort"), this.dispatchEvent("readystatechange"), this.dispatchEvent("abort")
        }};
        return e.XMLHttpRequest = l
    }),i(Nn, [Sn, it], function (e, t) {
        function n(e, n) {
            switch (n) {
                case"readAsText":
                case"readAsBinaryString":
                    return t.atob(e);
                case"readAsDataURL":
                    return e
            }
            return null
        }

        var i = {read: function (e, t) {
            var i, r = this.getRuntime();
            return(i = r.shimExec.call(this, "FileReaderSync", "readAsBase64", t.uid)) ? ("readAsDataURL" === e && (i = "data:" + (t.type || "") + ";base64," + i), n(i, e, t.type)) : null
        }};
        return e.FileReaderSync = i
    }),a([l, u, c, d, f, p, h, m, g, v, x, y, b, w, _, E, C, R, T, A, k, S, D, F, I, M, N, H, P, O, L, W, z, B, U, j, V, q, X, G, Y, $, J, K, Z, Q, et, tt, nt, it, rt, ot, at, st, lt, ut, ct, dt, ft, pt, ht, mt, gt, vt, xt, yt, bt, wt, _t, Et, Ct, Rt, Tt, At, kt, St, Dt, Ft, It, Mt, Nt, Ht, Pt, Ot, Lt, Wt, zt, Bt, Ut, jt, Vt, qt, Xt, Gt, Yt, $t, Jt, Kt, Zt, Qt, en, tn, nn, rn, on, an, sn, ln, un, cn, dn, fn, pn, hn, mn, gn, vn, xn, yn, bn, wn, _n, En, Cn, Rn, Tn, An, kn, Sn, Dn, Fn, In, Mn, Nn])
})(this);
