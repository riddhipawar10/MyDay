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
app.post("/api/auth/login", (req, res) => {
    try {
        const { email, password } = req.body.user;

        // Dummy validation (for testing)
        if (email === "riddhi@gmail.com" && password === "123456") {
            return res.json({
                user: {
                    email: email,
                    username: "riddhi",
                    token: "dummy-token-123",
                    bio: "",
                    image: ""
                }
            });
        }

        return res.status(400).json({
            message: "Invalid credentials"
        });

    } catch (error) {
        res.status(500).json({
            message: "Something went wrong"
        });
    }
});

// ================= SIGNUP =================
app.post("/api/auth/signup", (req, res) => {
    try {
        const { email, password, username } = req.body.user;

        return res.json({
            user: {
                email: email,
                username: username,
                token: "dummy-token-123",
                bio: "",
                image: ""
            }
        });

    } catch (error) {
        res.status(500).json({
            message: "Something went wrong"
        });
    }
});

// ================= SERVER =================
const PORT = process.env.PORT || 5000;

app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});