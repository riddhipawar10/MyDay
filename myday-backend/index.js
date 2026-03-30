const express = require("express");
const cors = require("cors");

const app = express();

// Middleware
app.use(cors());
app.use(express.json());

// Test Route
app.get("/", (req, res) => {
    res.send("MyDay Backend Running");
});

// ================= LOGIN =================
app.post("/api/users/login", (req, res) => {
    try {
        const { email, password } = req.body.user || {};

        if (!email || !password) {
            return res.status(400).json({ message: "Missing fields" });
        }

        if (email === "riddhi@gmail.com" && password === "123456") {
            return res.json({
                user: {
                    email,
                    username: "riddhi",
                    token: "dummy-token",
                    bio: "",
                    image: ""
                }
            });
        }

        return res.status(400).json({
            message: "Invalid credentials"
        });

    } catch (error) {
        res.status(500).json({ message: "Server error" });
    }
});

// ================= SIGNUP =================
app.post("/api/users", (req, res) => {
    try {
        const { email, password, username } = req.body.user || {};

        if (!email || !password || !username) {
            return res.status(400).json({ message: "Missing fields" });
        }

        res.json({
            user: {
                email,
                username,
                token: "dummy-token",
                bio: "",
                image: ""
            }
        });

    } catch (error) {
        res.status(500).json({ message: "Server error" });
    }
});

// ================= SERVER =================
const PORT = process.env.PORT || 5000;

app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});