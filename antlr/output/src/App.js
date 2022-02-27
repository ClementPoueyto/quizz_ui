import React, { Component } from "react";
import { Grommet, Grid, Box } from "grommet";

var quiz = require("./quiz.json");
export default class App extends Component {
  componentDidMount() {
    document.title = "SinglePageGoal";
  }

  render() {
    console.log("rootLayout1");
    console.log(
      "Theme [font=SCRIPT, name=globalTheme, primaryColor=BLUE, secondaryColor=ORANGE]"
    );
    return (
      <Grid
        rows={["small", "auto"]}
        columns={[]}
        gap="LARGE"
        areas={[
          { name: "header", start: [0, 0], end: [0, 0] },
          { name: "main", start: [0, 1], end: [0, 1] },
        ]}
      >
        <Box gridArea="header" background="brand" />
        <Box gridArea="main" background="brand" />
      </Grid>
    );
  }
}
