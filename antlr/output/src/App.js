import React, { Component } from 'react'
import {  onAnswerClick,  onTimerChange, } from './functions'
import { Grommet, Grid, Box, CheckBoxGroup, Text, Button, Clock, ResponsiveContext } from 'grommet'
import { deepMerge } from "grommet/utils";
import { grommet } from "grommet/themes";
var data = require('./quiz.json');

export default class App extends Component {

	constructor() {
		super();
		this.state = { quiz : data }
	}

	render() {
		var customBreakpoints = deepMerge(grommet, {
			global: {
				font: {
					family: 'SCRIPT'
				}
			}
		});
		var c_areas= []
		const areas = {
			default: [
				["nav","middle","right",],
				["footer","footer","footer",],
			],
		}
		const rows = {
			default:['large','small',],
		}
		const columns = {
			default:['medium','large','medium',],
}
		return (
			<Grommet>
				<ResponsiveContext.Consumer>
					{size =>
					<Grid
						rows={rows[size] ? rows[size] : rows["default"]}
						columns={columns[size] ? columns[size] : columns["default"]}
					gap='null'
						areas={areas[size] ? areas[size] : areas["default"]}>
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("middle") >=0) ?
						<Box gridArea='middle' align='center' background='light-2'>
						<Text size='medium'  textAlign='center'  >{this.state.quiz.questions[this.state.quiz.indexQuestion].statement}</Text>
						{this.state.quiz.questions[this.state.quiz.indexQuestion].answers.map((item,index)=>{
							return <Button primary={true}  size='small'  margin='small'  color='LIGHT-3'  onClick={()=>{ this.setState({ quiz : onAnswerClick(this.state.quiz,item,index)})}}  label={this.state.quiz.questions[this.state.quiz.indexQuestion].answers[index]}  />
						})}
						</Box>
						:
						<Box/>
					}
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("nav") >=0) ?
						<Box gridArea='nav' align='center' background='brand'>
						<Text size='large'  textAlign='center'  >{this.state.quiz.title}</Text>
						<Text size='large'  textAlign='center'  >{this.state.quiz.theme}</Text>
						</Box>
						:
						<Box/>
					}
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("right") >=0) ?
						<Box gridArea='right' align='center' background='brand'>
						<Clock run='backward'  type='digital'  size='large'  time='T00:01:00'  alignSelf='center'  precision='seconds'  onChange={(time)=>{ this.setState({ quiz : onTimerChange(this.state.quiz,time)})}}  />
						</Box>
						:
						<Box/>
					}
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("footer") >=0) ?
						<Box gridArea='footer' align='center' background='light-3'>
						</Box>
						:
						<Box/>
					}
				</Grid>
			}
			</ResponsiveContext.Consumer>
		</Grommet>
		);
	}
}
